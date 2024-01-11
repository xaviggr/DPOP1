package Persistence;

import Bussines.Catalog;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Shop;
import Persistence.exception.PersistenceJsonException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa operaciones de lectura y escritura específicas para tiendas en formato JSON.
 * Extiende la clase abstracta DAOJSON y utiliza Gson para la serialización y deserialización de datos.
 *
 * @see DAOJSON
 * @see com.google.gson.Gson
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class ShopDAO extends DAOJSON {

    /**
     * Constructor que establece la ruta del archivo JSON asociado a las tiendas.
     * Verifica si el archivo existe y lo crea si no.
     */
    public ShopDAO() {
        this.path += "shops.json";
        try {
            checkIfFileExists();
        } catch (FileNotFoundException e) {
            try {
                createFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Obtiene una tienda por su nombre.
     *
     * @param shopName El nombre de la tienda a buscar.
     * @return La tienda encontrada, o null si no se encontró ninguna tienda con ese nombre.
     */
    public Shop getShop(String shopName) throws PersistenceJsonException {
        List<Shop> shopsList = getShops();
        return findShopByName(shopsList, shopName);
    }

    /**
     * Obtiene una lista de todas las tiendas almacenadas en el archivo JSON.
     *
     * @return Una lista de objetos Shop que representan todas las tiendas.
     */
    public List<Shop> getShops() throws PersistenceJsonException {
        JsonArray shops = readAllFromFile();
        List<Shop> shopsList = new ArrayList<>();

        for (JsonElement shopElement : shops) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            shopsList.add(createShopFromJsonObject(shopObject));
        }

        return shopsList;
    }

    /**
     * Obtiene una lista de tiendas donde un producto específico existe en el catálogo.
     *
     * @param productName El nombre del producto a buscar en los catálogos de las tiendas.
     * @return Una lista de tiendas que tienen el producto en su catálogo.
     */
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        List<Shop> shopsWithProduct = new ArrayList<>();

        for (Shop shop : shops) {
            if (shop.getCatalog().productExists(productName)) {
                shopsWithProduct.add(shop);
            }
        }

        return shopsWithProduct;
    }

    /**
     * Obtiene una lista de productos de una tienda específica.
     *
     * @param shopName El nombre de la tienda.
     * @return Una lista de objetos ShopProduct que representan los productos de la tienda.
     */
    public List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException {
        return getShop(shopName).getCatalog().getProducts();
    }


    /**
     * Obtiene un producto específico de una tienda por su nombre.
     *
     * @param shopName       El nombre de la tienda.
     * @param productName    El nombre del producto.
     * @return El objeto ShopProduct encontrado, o null si no se encontró.
     */
    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException {
        List<ShopProduct> products = getProductsFromShop(shopName);

        for (ShopProduct currentProduct: products) {
            if (currentProduct.getProductName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }


    /**
     * Agrega un producto a la tienda especificada.
     *
     * @param shopName     El nombre de la tienda.
     * @param shopProduct  El objeto ShopProduct que se va a agregar.
     */
    public void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            shop.getCatalog().addProduct(shopProduct);
            saveShopsToFile(shops);
        }
    }

    /**
     * Actualiza la información de un producto específico en la tienda.
     *
     * @param shopName     El nombre de la tienda.
     * @param shopProduct  El objeto ShopProduct con la información actualizada.
     */
    public void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            Catalog catalog = shop.getCatalog();
            if (catalog.updateProduct(shopProduct)) {
                saveShopsToFile(shops);
            }
        }
    }

    /**
     * Elimina un producto específico de la tienda por su nombre.
     *
     * @param shopName        El nombre de la tienda.
     * @param shopProductName El nombre del producto a eliminar.
     */
    public void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            Catalog catalog = shop.getCatalog();
            if (catalog.removeProduct(shopProductName)) {
                saveShopsToFile(shops);
            }
        }
    }

    /**
     * Encuentra una tienda por su nombre en una lista dada.
     *
     * @param shops     La lista de tiendas.
     * @param shopName  El nombre de la tienda a buscar.
     * @return La tienda encontrada, o null si no se encontró ninguna tienda con ese nombre.
     */
    public Shop findShopByName(List<Shop> shops, String shopName) {
        for (Shop currentShop : shops) {
            if (currentShop.getName().equals(shopName)) {
                return currentShop;
            }
        }
        return null;
    }

    /**
     * Agrega una nueva tienda al archivo JSON.
     *
     * @param shop La tienda a agregar.
     */
    public void addShop(Shop shop) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        shops.add(shop);
        saveShopsToFile(shops);
    }

    /**
     * Crea una tienda a partir de un JsonObject obtenido de la lectura del archivo JSON.
     *
     * @param shopObject El JsonObject que representa la tienda en formato JSON.
     * @return Un objeto Shop creado a partir del JsonObject.
     */
    private Shop createShopFromJsonObject(JsonObject shopObject) {
        String name = shopObject.get("name").getAsString();
        String description = shopObject.get("description").getAsString();
        int foundationYear = shopObject.get("since").getAsInt();
        double earnings = shopObject.get("earnings").getAsDouble();
        String businessModel = shopObject.get("businessModel").getAsString();

        JsonArray shopProducts = shopObject.get("catalogue").getAsJsonArray();
        List<ShopProduct> products = new ArrayList<>();

        for (JsonElement shopProductElement : shopProducts) {
            JsonObject shopProductObject = shopProductElement.getAsJsonObject();
            String product_name = shopProductObject.get("name").getAsString();
            String brand = shopProductObject.get("brand").getAsString();
            String category = shopProductObject.get("category").getAsString();
            double mrp = shopProductObject.get("mrp").getAsDouble();
            double price = shopProductObject.get("price").getAsDouble();
            products.add(new ShopProduct(product_name, brand, mrp, ProductCategory.valueOf(category), price));
        }

        return new Shop(name, description, foundationYear, earnings, businessModel, new Catalog(products));
    }

    /**
     * Guarda la información de todas las tiendas en el archivo JSON.
     *
     * @param shops La lista de tiendas a guardar.
     */
    private void saveShopsToFile(List<Shop> shops) throws PersistenceJsonException {
        JsonArray jsonArray = new JsonArray();

        for (Shop shop : shops) {
            JsonObject shopObject = new JsonObject();
            shopObject.addProperty("name", shop.getName());
            shopObject.addProperty("description", shop.getDescription());
            shopObject.addProperty("since", shop.getFoundationYear());
            shopObject.addProperty("earnings", shop.getEarnings());
            shopObject.addProperty("businessModel", shop.getBusinessModel());

            JsonArray shopProducts = new JsonArray();

            for (ShopProduct shopProduct : shop.getCatalog().getProducts()) {
                JsonObject productObject = new JsonObject();
                productObject.addProperty("name", shopProduct.getProductName());
                productObject.addProperty("brand", shopProduct.getBrand());
                productObject.addProperty("category", shopProduct.getCategory().toString());
                productObject.addProperty("mrp", shopProduct.getMaxPrice());
                productObject.addProperty("price", shopProduct.getProductPrice());
                shopProducts.add(productObject);
            }

            shopObject.add("catalogue", shopProducts);
            jsonArray.add(shopObject);
        }
        saveToFile(jsonArray);
    }

    /**
     * Elimina un producto de todas las tiendas por su nombre.
     *
     * @param nameProduct El nombre del producto a eliminar de todas las tiendas.
     */
    public void removeProductFromShops(String nameProduct) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        for (Shop shop : shops) {
            shop.getCatalog().removeProduct(nameProduct);
        }
        saveShopsToFile(shops);
    }

    /**
     * Obtiene una lista de nombres de todas las tiendas almacenadas en el archivo JSON.
     *
     * @return Una lista de nombres de tiendas.
     */
    public List<String> getAllNameShops() throws PersistenceJsonException {
        List<Shop> shops = getShops();
        List<String> names = new ArrayList<>();
        for (Shop shop : shops) {
            names.add(shop.getName());
        }
        return names;
    }

    /**
     * Obtiene una lista de nombres de todos los productos de una tienda específica.
     *
     * @param shopName El nombre de la tienda.
     * @return Una lista de nombres de productos de la tienda.
     */
    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException {
        List<ShopProduct> products = getProductsFromShop(shopName);
        List<String> names = new ArrayList<>();
        for (ShopProduct product : products) {
            names.add(product.getProductName());
        }
        return names;
    }

    /**
     * Actualiza las ganancias de una tienda específica.
     *
     * @param shop La tienda con las ganancias actualizadas.
     */
    public void updateShop(Shop shop) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        for (Shop currentShop : shops) {
            if (currentShop.getName().equals(shop.getName())) {
                currentShop.setEarnings(shop.getEarnings());
            }
        }
        saveShopsToFile(shops);
    }
}
