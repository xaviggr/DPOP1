package Persistence;

import Bussines.Product.GeneralProduct;
import Bussines.Product.Product;
import Bussines.Product.ReducedTaxesProduct;
import Bussines.Product.SuperReducedTaxes;
import Bussines.Review;
import Persistence.exception.PersistenceJsonException;
import com.google.gson.Gson;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOApi implements ProductDAO {

    private static final String http = "https://balandrau.salle.url.edu/dpoo/P1-G160/products";
    private final ApiHelper apiHelper;
    public ProductDAOApi() {
        try {
            apiHelper = new ApiHelper();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    private String productToJson(Product product) {
        StringBuilder json = new StringBuilder("{");

        json.append("\"name\": \"").append(product.getProductName()).append("\",");
        json.append("\"brand\": \"").append(product.getBrand()).append("\",");
        json.append("\"mrp\": ").append(product.getMaxPrice()).append(",");
        json.append("\"category\": \"").append(product.getCategory()).append("\",");
        json.append("\"reviews\": [");

        product.getReviews().forEach(review -> {
            json.append("{")
                    .append("\"stars\": ").append(review.getStars()).append(",")
                    .append("\"commentary\": \"").append(review.getCommentary()).append("\"")
                    .append("},");
        });

        if (!product.getReviews().isEmpty()) {
            json.deleteCharAt(json.length() - 1);
        }

        json.append("]}");
        return json.toString();
    }

    private Product jsonToProduct(String jsonResponse) {
        Product product;
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        String brand = jsonObject.get("brand").getAsString();
        double maxPrice = jsonObject.get("mrp").getAsDouble();
        String category = jsonObject.get("category").getAsString();

        product = switch (category) {
            case "ReducedTaxesProduct" -> new ReducedTaxesProduct(name, brand, maxPrice);
            case "SuperReducedTaxesProduct" -> new SuperReducedTaxes(name, brand, maxPrice);
            default -> new GeneralProduct(name, brand, maxPrice);
        };

        JsonArray reviewsArray = jsonObject.getAsJsonArray("reviews");
        for (JsonElement reviewElement : reviewsArray) {
            JsonObject reviewObject = reviewElement.getAsJsonObject();
            String stars = "*".repeat(Math.max(0, reviewObject.get("stars").getAsInt()));
            String commentary = reviewObject.get("commentary").getAsString();
            Review review = new Review(stars, commentary);
            product.addReview(review);
        }

        return product;
    }

    private List<Product> jsonToProducts(String jsonResponse) {
        List<Product> products = new ArrayList<>();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (JsonElement productElement : jsonArray) {
            JsonObject productObject = productElement.getAsJsonObject();
            products.add(jsonToProduct(productObject.toString()));
        }

        return products;
    }

    @Override
    public void addProduct(Product product) throws ApiException {
        apiHelper.postToUrl(http, productToJson(product));
    }

    @Override
    public void removeProduct(String productName) throws ApiException {
        apiHelper.deleteFromUrl(http + "?name=" + productName);
    }

    @Override
    public Product findProduct(String productName) throws ApiException {
        String jsonResponse = apiHelper.getFromUrl(http + "?name=" + productName);
        List<Product> products = jsonToProducts(jsonResponse);

        for (Product product : products) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() throws ApiException {
        String jsonResponse = apiHelper.getFromUrl(http);
        List<Product> products = new ArrayList<>();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonResponse, JsonElement.class);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (JsonElement productElement : jsonArray) {
            JsonObject productObject = productElement.getAsJsonObject();
            products.add(jsonToProduct(productObject.toString()));
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) throws ApiException {
        removeProduct(product.getProductName());
        addProduct(product);
    }

    @Override
    public List<Product> findProductsByQuery(String query) throws ApiException {
        List<Product> products = new ArrayList<>();
        String jsonResponse = apiHelper.getFromUrl(http + "?name=" + query);
        products = jsonToProducts(jsonResponse);
        String jsonResponse2 = apiHelper.getFromUrl(http + "?brand=" + query);
        products.addAll(jsonToProducts(jsonResponse2));
        return products;
    }

    @Override
    public void checkIfFileExists() {

    }
}
