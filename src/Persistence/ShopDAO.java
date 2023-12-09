package Persistence;

import Bussines.Catalog;
import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Shop;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ShopDAO extends DAOJSON {
    public ShopDAO() {
        this.path = "shops.json";
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

    public Shop getShop(String shopName) {
        List<Shop> shopsList = getShops();
        return findShopByName(shopsList, shopName);
    }

    public List<Shop> getShops() {
        JsonArray shops = readAllFromFile();
        List<Shop> shopsList = new ArrayList<>();

        for (JsonElement shopElement : shops) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            shopsList.add(createShopFromJsonObject(shopObject));
        }

        return shopsList;
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(ShopProduct shopProduct) {
        List<Shop> shopsList = getShops();
        List<Shop> shopsWhereProductExists = new ArrayList<>();

        for (Shop currentShop: shopsList) {
            if (currentShop.getCatalog().getProducts().contains(shopProduct)) {
                shopsWhereProductExists.add(currentShop);
            }
        }

        return shopsWhereProductExists;
    }

    public List<ShopProduct> getProductsFromShop(String shopName) {
        return getShop(shopName).getCatalog().getProducts();
    }

    public Product getProductFromShop(String shopName, String productName) {
        List<ShopProduct> products = getProductsFromShop(shopName);

        for (ShopProduct currentProduct: products) {
            if (currentProduct.getProductName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }

    public boolean addProductInShop(String shopName, ShopProduct shopProduct) {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            shop.getCatalog().addProduct(shopProduct);
            return saveShopsToFile(shops);
        }

        return false;
    }

    public boolean updateProductFromShop(String shopName, ShopProduct shopProduct) {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            Catalog catalog = shop.getCatalog();
            if (catalog.updateProduct(shopProduct)) {
                return saveShopsToFile(shops);
            }
        }

        return false;
    }

    public boolean removeProductFromShop(String shopName, String shopProductName) {
        List<Shop> shops = getShops();
        Shop shop = findShopByName(shops, shopName);

        if (shop != null) {
            Catalog catalog = shop.getCatalog();
            if (catalog.removeProduct(shopProductName)) {
                return saveShopsToFile(shops);
            }
        }

        return false;
    }

    private Shop findShopByName(List<Shop> shops, String shopName) {
        for (Shop currentShop : shops) {
            if (currentShop.getName().equals(shopName)) {
                return currentShop;
            }
        }
        return null;
    }

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

    private boolean saveShopsToFile(List<Shop> shops) {
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
        return saveToFile(jsonArray);
    }
}
