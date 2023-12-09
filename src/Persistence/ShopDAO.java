package Persistence;

import Bussines.Product.Product;
import Bussines.Product.ShopProduct;
import Bussines.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

        for (int i = 0; i < shops.size(); i++) {
            if (Objects.equals(shops.get(i).getAsJsonObject().get("name").getAsString(), shopName)) {
                Gson gson = new Gson();
                return gson.fromJson(shops.get(i), Shop.class);
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
