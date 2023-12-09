package Persistence;

import Bussines.Catalog;
import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Review;
import Bussines.Shop;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        JsonArray shops = readAllFromFile();

        for (JsonElement shopElement : shops) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            if (shopObject.get("name").getAsString().equals(shopName)) {
                String name = shopObject.get("name").getAsString();
                String description = shopObject.get("description").getAsString();
                int foundationYear = shopObject.get("since").getAsInt();
                double earnings = shopObject.get("earnings").getAsDouble();
                String businessModel = shopObject.get("businessModel").getAsString();

                JsonArray shopProducts = shopObject.get("catalogue").getAsJsonArray();
                List<ShopProduct> products = new ArrayList<>();

                for (JsonElement shopProductElement : shopProducts) {
                    JsonObject shopProductObject = shopProductElement.getAsJsonObject();
                    String product_name = shopProductObject.get("name").toString();
                    String brand = shopProductObject.get("brand").getAsString();
                    String category = shopProductObject.get("category").getAsString();
                    double mrp = shopProductObject.get("mrp").getAsDouble();
                    double price = shopProductObject.get("price").getAsDouble();
                    products.add(new ShopProduct(product_name, brand, mrp, ProductCategory.valueOf(category), price));
                }

                return new Shop(name, description, foundationYear, earnings, businessModel, new Catalog(products));
            }
        }

        return null;
    }

    public List<Shop> getShops() {
        return null;
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(ShopProduct shopProduct) {
        return null;
    }

    public List<Product> getProductsFromShop(String shopName) {
        return null;
    }

    public Product getProductFromShop(String shopName, String productName) {
        return null;
    }

    public boolean addProductInShop(String shopName, ShopProduct shopProduct) {
        return false;
    }

    public boolean updateProductFromShop(String shopName, ShopProduct shopProduct) {
        return false;
    }

    public boolean removeProductFromShop(String shopName, ShopProduct shopProduct) {
        return false;
    }
}
