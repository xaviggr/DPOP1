package Persistence;
import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Review;
import com.google.gson.*;

import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ProductDAO extends DAOJSON {

    public ProductDAO() {
        this.path = "products.json";
    }

    private JsonObject productToJson(Product product) {
        JsonObject productJson = new JsonObject();
        productJson.addProperty("name", product.getProductName());
        productJson.addProperty("brand", product.getBrand());
        productJson.addProperty("mrp", product.getMaxPrice());
        productJson.addProperty("category", product.getCategory().toString());
        productJson.add("reviews", reviewsToJsonArray(product.getReviews()));
        return productJson;
    }

    private JsonArray reviewsToJsonArray(List<Review> reviews) {
        JsonArray reviewsArray = new JsonArray();
        for (Review review : reviews) {
            JsonObject reviewObject = new JsonObject();
            reviewObject.addProperty("stars", review.getStars());
            reviewObject.addProperty("commentary", review.getCommentary());
            reviewsArray.add(reviewObject);
        }
        return reviewsArray;
    }

    private Product jsonToProduct(JsonObject productObject) {
        String name = productObject.get("name").getAsString();
        String brand = productObject.get("brand").getAsString();
        Double mrp = productObject.get("mrp").getAsDouble();
        String category = productObject.get("category").getAsString();

        Product product = new Product(name, brand, mrp, ProductCategory.valueOf(category));

        JsonArray reviewsArray = productObject.get("reviews").getAsJsonArray();
        for (JsonElement reviewElement : reviewsArray) {
            JsonObject reviewObject = reviewElement.getAsJsonObject();
            String stars = reviewObject.get("stars").toString();
            String commentary = reviewObject.get("commentary").getAsString();
            Review review = new Review(stars, commentary);
            product.addReview(review);
        }

        return product;
    }

    public boolean addProduct(Product product) {
        JsonArray products = readAllFromFile();
        JsonObject newProduct = productToJson(product);
        products.add(newProduct);
        return saveToFile(products);
    }

    public boolean removeProduct(String productName) {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(productName)) {
                products.remove(productElement);
                return saveToFile(products);
            }
        }

        return false;
    }

    public Product findProduct(String productName) {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(productName)) {
                return jsonToProduct(productObject);
            }
        }
        return null;
    }
}
