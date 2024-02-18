package Persistence;

import Bussines.Product.*;
import Bussines.Shop.LoyaltyShop;
import Bussines.Shop.MaximumProfitShop;
import Bussines.Shop.Shop;
import Bussines.Shop.SponsoredShop;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class ShopDAOApi implements ShopDAO {

    private static final String http = "https://balandrau.salle.url.edu/dpoo/P1-G160/shops";
    private final ApiHelper apiHelper;
    public ShopDAOApi() {
        try {
            apiHelper = new ApiHelper();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
    private Shop jsonToShop(String jsonResponse) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        int foundationYear = jsonObject.get("since").getAsInt();
        double earnings = jsonObject.get("earnings").getAsDouble();
        String businessModel = jsonObject.get("businessModel").getAsString();

        JsonArray shopProducts = jsonObject.get("catalogue").getAsJsonArray();
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
            case "LoyaltyShop" -> new LoyaltyShop(name, description, foundationYear, earnings, products);
            case "SponsoredShop" -> new SponsoredShop(name, description, foundationYear, earnings, products);
            default -> null;
        };
    }
    private List<Shop> jsonToShops(String jsonResponse) {
        List<Shop> shops = new ArrayList<>();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (JsonElement shopElement : jsonArray) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            shops.add(jsonToShop(shopObject.toString()));
        }

        return shops;
    }
    private String shopToJson(Shop shop) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", shop.getName());
        jsonObject.addProperty("description", shop.getDescription());
        jsonObject.addProperty("since", shop.getFoundationYear());
        jsonObject.addProperty("earnings", shop.getEarnings());
        jsonObject.addProperty("businessModel", shop.getBusinessModel());

        JsonArray shopProducts = new JsonArray();
        for (ShopProduct shopProduct : shop.getCatalog()) {
            JsonObject shopProductObject = new JsonObject();
            shopProductObject.addProperty("name", shopProduct.getProductName());
            shopProductObject.addProperty("brand", shopProduct.getProduct().getBrand());
            shopProductObject.addProperty("category", shopProduct.getProduct().getClass().getSimpleName());
            shopProductObject.addProperty("mrp", shopProduct.getProduct().getMaxPrice());
            shopProductObject.addProperty("price", shopProduct.getProductPrice());
            shopProducts.add(shopProductObject);
        }
        jsonObject.add("catalogue", shopProducts);

        return jsonObject.toString();
    }
    @Override
    public Shop getShop(String shopName) throws ApiException {
        String jsonResponse = apiHelper.getFromUrl(http + "?name=" + shopName);
        List<Shop> shops = jsonToShops(jsonResponse);

        for (Shop shop : shops) {
            if (shop.getName().equals(shopName)) {
                return shop;
            }
        }

        return null;
    }
    @Override
    public List<Shop> getShops() throws ApiException {
        String jsonResponse = apiHelper.getFromUrl(http);
        List<Shop> shopsList = new ArrayList<>();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);
        JsonArray shops = jsonElement.getAsJsonArray();

        for (JsonElement shopElement : shops) {
            JsonObject shopObject = shopElement.getAsJsonObject();
            shopsList.add(jsonToShop(shopObject.toString()));
        }

        return shopsList;
    }
    @Override
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws ApiException {
        List<Shop> shops = getShops();
        List<Shop> shopsWithProduct = new ArrayList<>();

        for (Shop shop : shops) {
            for (ShopProduct product : shop.getCatalog()) {
                if(product.getProductName().equals(productName)) {
                    shopsWithProduct.add(shop);
                }
            }
        }

        return shopsWithProduct;
    }
    @Override
    public List<String> getAllNameShops() throws ApiException {
        List<Shop> shops = getShops();
        List<String> names = new ArrayList<>();

        for (Shop shop : shops) {
            names.add(shop.getName());
        }

        return names;
    }
    @Override
    public void addShop(Shop shop) throws ApiException {
        String json = shopToJson(shop);
        apiHelper.postToUrl(http, json);
    }
    @Override
    public void updateShop(Shop shop) throws ApiException {
        String json = shopToJson(shop);
        apiHelper.deleteFromUrl(http + "?name=" + shop.getName());
        apiHelper.postToUrl(http, json);
    }
    @Override
    public List<ShopProduct> getProductsFromShop(String shopName) throws ApiException {
        return getShop(shopName).getCatalog();
    }
    @Override
    public void addProductInShop(String shopName, ShopProduct shopProduct) throws ApiException {
        Shop shop = getShop(shopName);
        shop.addProduct(shopProduct);
        updateShop(shop);
    }
    @Override
    public void updateProductFromShop(String shopName, ShopProduct shopProduct) throws ApiException {
        Shop shop = getShop(shopName);
        shop.updateProduct(shopProduct);
        updateShop(shop);
    }
    @Override
    public void removeProductFromShop(String shopName, String shopProductName) throws ApiException {
        Shop shop = getShop(shopName);
        shop.removeProduct(shopProductName);
        updateShop(shop);
    }
    @Override
    public void removeProductFromShops(String nameProduct) throws ApiException {
        List<Shop> shops = getShops();
        for (Shop shop : shops) {
            shop.removeProduct(nameProduct);
            updateShop(shop);
        }
    }
    @Override
    public List<String> getAllProductsNameFromShop(String shopName) throws ApiException {
        List<ShopProduct> products = getProductsFromShop(shopName);
        List<String> names = new ArrayList<>();

        for (ShopProduct product : products) {
            names.add(product.getProductName());
        }

        return names;
    }
    @Override
    public ShopProduct getProductFromShop(String shopName, String productName) throws ApiException {
        List<ShopProduct> products = getProductsFromShop(shopName);

        for (ShopProduct currentProduct: products) {
            if (currentProduct.getProductName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }
}
