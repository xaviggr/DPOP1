package Persistence;
import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Review;
import com.google.gson.*;

public class ProductDAO extends DAOJSON {

    public ProductDAO() {
        this.path = "products.json";
    }

    public boolean addProduct(Product product) {
        JsonArray products = readAllFromFile();

        JsonObject newProduct = new JsonObject();
        newProduct.addProperty("name", product.getProductName());
        newProduct.addProperty("brand", product.getBrand());
        newProduct.addProperty("mrp", product.getMaxPrice());
        newProduct.addProperty("category", product.getCategory().toString());

        JsonArray reviewsArray = new JsonArray();
        for (Review review : product.getReviews()) {
            JsonObject reviewObject = new JsonObject();
            reviewObject.addProperty("stars", review.getStars());
            reviewObject.addProperty("commentary", review.getCommentary());
            reviewsArray.add(reviewObject);
        }
        newProduct.add("reviews", reviewsArray);

        products.add(newProduct);

        return saveToFile(products);
    }

    public boolean removeProduct(String productName) {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(productName)) {
                System.out.println(productElement);
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
        }

        return null;
    }


}
