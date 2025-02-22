package Persistence;

import Bussines.Product.*;
import Bussines.Shop.LoyaltyShop;
import Bussines.Shop.MaximumProfitShop;
import Bussines.Shop.Shop;
import Bussines.Shop.SponsoredShop;
import Persistence.exception.PersistenceJsonException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz ShopDAO que utiliza un archivo JSON para almacenar y acceder a los datos de las tiendas.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ShopDAOJSON extends DAOJSON implements ShopDAO {
    /**
     * Constructor que inicializa el path del archivo JSON y verifica si existe o lo crea si no.
     */
    public ShopDAOJSON() {
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
     * Crea un objeto Shop a partir de un JsonObject.
     *
     * @param shopObject JsonObject que representa la tienda.
     * @return Objeto Shop creado a partir del JsonObject.
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

            Product product = switch (category) {
                case "ReducedTaxesProduct" -> new ReducedTaxesProduct(product_name, brand, mrp);
                case "SuperReducedTaxes" -> new SuperReducedTaxes(product_name, brand, mrp);
                default -> new GeneralProduct(product_name, brand, mrp);
            };

            products.add(new ShopProduct(product, price));
        }


        return switch (businessModel) {
            case "MaximumProfitShop" -> new MaximumProfitShop(name, description, foundationYear, earnings, products);
            case "LoyaltyShop" ->  {
                double threshold = shopObject.get("threshold").getAsDouble();
                yield new LoyaltyShop(name, description, foundationYear, earnings, products,threshold);
            }
            case "SponsoredShop" ->  {
                String brand = shopObject.get("sponsored_brand").getAsString();
                yield new SponsoredShop(name, description, foundationYear, earnings, products,brand);
            }
            default -> null;
        };
    }
    /**
     * Guarda la lista de tiendas en el archivo JSON.
     *
     * @param shops Lista de tiendas.
     * @throws PersistenceJsonException Si hay un error al escribir en el archivo JSON.
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
            switch (shop.getBusinessModel()) {
                case "LoyaltyShop" ->  {
                    if (shop instanceof LoyaltyShop loyaltyShop) {
                        shopObject.addProperty("threshold", loyaltyShop.getThreshold());
                    }
                }
                case "SponsoredShop" ->  {
                    if (shop instanceof SponsoredShop sponsoredShop) {
                        shopObject.addProperty("sponsored_brand", sponsoredShop.getBrand());
                    }
                }
            }

            JsonArray shopProducts = getShopProducts(shop);
            shopObject.add("catalogue", shopProducts);
            jsonArray.add(shopObject);
        }
        saveToFile(jsonArray);
    }
    /**
     * Obtiene un JsonArray de productos de tienda a partir de una tienda.
     *
     * @param shop Tienda de la cual obtener los productos.
     * @return JsonArray de productos de tienda.
     */
    private JsonArray getShopProducts(Shop shop) {
        JsonArray shopProducts = new JsonArray();

        for (ShopProduct shopProduct : shop.getCatalog()) {
            JsonObject productObject = new JsonObject();
            productObject.addProperty("name", shopProduct.getProductName());
            productObject.addProperty("brand", shopProduct.getBrand());
            productObject.addProperty("category", shopProduct.getProduct().getCategory());
            productObject.addProperty("mrp", shopProduct.getMaxPrice());
            productObject.addProperty("price", shopProduct.getProductPrice());
            shopProducts.add(productObject);
        }
        return shopProducts;
    }
    /**
     * Encuentra una tienda por nombre en la lista de tiendas.
     *
     * @param shops     Lista de tiendas.
     * @param shopName  Nombre de la tienda a buscar.
     * @return          Tienda encontrada o null si no se encuentra.
     */
    private Shop findShopByName(List<Shop> shops, String shopName) {
        for (Shop currentShop : shops) {
            if (currentShop.getName().equals(shopName)) {
                return currentShop;
            }
        }
        return null;
    }
    @Override
    public Shop getShop(String shopName) throws PersistenceJsonException {
        List<Shop> shopsList = this.getShops();
        return this.findShopByName(shopsList, shopName);
    }
    @Override
    public List<Shop> getShops() throws PersistenceJsonException {
        JsonArray shops = readAllFromFile();
        List<Shop> shopsList = new ArrayList<>();

        for (JsonElement shopElement : shops) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            shopsList.add(createShopFromJsonObject(shopObject));
        }

        return shopsList;
    }
    @Override
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        List<Shop> shopsWithProduct = new ArrayList<>();

        for (Shop shop : shops) {
            if (shop.productExists(productName)) {
                shopsWithProduct.add(shop);
            }
        }

        return shopsWithProduct;
    }
    @Override
    public List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException {
        return this.getShop(shopName).getCatalog();
    }
    @Override
    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException {
        List<ShopProduct> products = getProductsFromShop(shopName);

        for (ShopProduct currentProduct: products) {
            if (currentProduct.getProductName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }
    @Override
    public void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {
        List<Shop> shops = this.getShops();
        Shop shop = this.findShopByName(shops, shopName);
        if (shop != null) {
            shop.addProduct(shopProduct);
            this.saveShopsToFile(shops);
        }

    }
    @Override
    public void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {
        List<Shop> shops = this.getShops();
        Shop shop = this.findShopByName(shops, shopName);
        if (shop != null && shop.updateProduct(shopProduct)) {
            this.saveShopsToFile(shops);
        }

    }
    @Override
    public void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException {
        List<Shop> shops = this.getShops();
        Shop shop = this.findShopByName(shops, shopName);
        if (shop != null && shop.removeProduct(shopProductName)) {
            this.saveShopsToFile(shops);
        }

    }
    @Override
    public void addShop(Shop shop) throws PersistenceJsonException {
        List<Shop> shops = this.getShops();
        shops.add(shop);
        this.saveShopsToFile(shops);
    }
    @Override
    public void removeProductFromShops(String nameProduct) throws PersistenceJsonException {
        List<Shop> shops = getShops();
        for (Shop shop : shops) {
            shop.removeProduct(nameProduct);
        }
        saveShopsToFile(shops);
    }
    @Override
    public List<String> getAllNameShops() throws PersistenceJsonException {
        List<Shop> shops = getShops();
        List<String> names = new ArrayList<>();
        for (Shop shop : shops) {
            names.add(shop.getName());
        }
        return names;
    }
    @Override
    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException {
        List<ShopProduct> products = getProductsFromShop(shopName);
        List<String> names = new ArrayList<>();
        for (ShopProduct product : products) {
            names.add(product.getProductName());
        }
        return names;
    }
    @Override
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