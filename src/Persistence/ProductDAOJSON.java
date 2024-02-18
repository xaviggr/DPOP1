package Persistence;

import Bussines.Product.GeneralProduct;
import Bussines.Product.Product;
import Bussines.Product.ReducedTaxesProduct;
import Bussines.Product.SuperReducedTaxes;
import Bussines.Review;
import Persistence.exception.PersistenceJsonException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que implementa la interfaz ProductDAO y extiende la clase DAOJSON.
 * Proporciona métodos para realizar operaciones de acceso a datos utilizando archivos JSON.
 */
public class ProductDAOJSON extends DAOJSON implements ProductDAO {
    /**
     * Constructor de ProductDAOJSON que inicializa la ruta del archivo JSON.
     */
    public ProductDAOJSON() {
        this.path = this.path + "products.json";
    }
    /**
     * Método privado para convertir un objeto Product a formato JSON.
     *
     * @param product Producto que se va a convertir a JSON.
     * @return Objeto JSON representando el producto.
     */
    private JsonObject productToJson(Product product) {
        JsonObject productJson = new JsonObject();
        productJson.addProperty("name", product.getProductName());
        productJson.addProperty("brand", product.getBrand());
        productJson.addProperty("mrp", product.getMaxPrice());
        productJson.addProperty("category", product.getCategory());
        productJson.add("reviews", this.reviewsToJsonArray(product.getReviews()));
        return productJson;
    }
    /**
     * Método privado para convertir una lista de objetos Review a formato JSON.
     *
     * @param reviews Lista de reviews que se va a convertir a JSON.
     * @return Objeto JSON representando las reviews.
     */
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
    /**
     * Método privado para convertir un objeto JSON a un objeto Product.
     *
     * @param productObject Objeto JSON que se va a convertir a Product.
     * @return Objeto Product obtenido del JSON.
     */
    private Product jsonToProduct(JsonObject productObject) {
        String name = productObject.get("name").getAsString();
        String brand = productObject.get("brand").getAsString();
        double mrp = productObject.get("mrp").getAsDouble();

        Product product = switch (productObject.get("category").getAsString()) {
            case "ReducedTaxesProduct" -> new ReducedTaxesProduct(name, brand, mrp);
            case "SuperReducedTaxesProduct" -> new SuperReducedTaxes(name, brand, mrp);
            default -> new GeneralProduct(name, brand, mrp);
        };

        JsonArray reviewsArray = productObject.get("reviews").getAsJsonArray();
        for (JsonElement reviewElement : reviewsArray) {
            JsonObject reviewObject = reviewElement.getAsJsonObject();
            String stars = "*".repeat(Math.max(0, reviewObject.get("stars").getAsInt()));

            String commentary = reviewObject.get("commentary").getAsString();
            Review review = new Review(stars, commentary);
            product.addReview(review);
        }

        return product;
    }

    @Override
    public void addProduct(Product product) throws PersistenceJsonException {
        JsonArray products = this.readAllFromFile();
        JsonObject newProduct = this.productToJson(product);
        products.add(newProduct);
        this.saveToFile(products);
    }

    @Override
    public void updateProduct(Product product) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(product.getProductName())) {
                productObject.addProperty("brand", product.getBrand());
                productObject.addProperty("mrp", product.getMaxPrice());
                productObject.addProperty("category", product.getCategory());
                productObject.add("reviews", reviewsToJsonArray(product.getReviews()));
                saveToFile(products);
                return;
            }
        }
    }

    @Override
    public void removeProduct(String productName) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(productName)) {
                products.remove(productElement);
                saveToFile(products);
                return;
            }
        }
    }

    @Override
    public Product findProduct(String productName) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(productName)) {
                return jsonToProduct(productObject);
            }
        }
        return null;
    }

    @Override
    public List<Product> findProductsByQuery(String query) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();
        List<Product> productList = new ArrayList<>();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            String productName = productObject.get("name").getAsString();
            String brand = productObject.get("brand").getAsString();

            if (productName.toLowerCase().contains(query.toLowerCase())) {
                productList.add(jsonToProduct(productObject));
            }
            else if (brand.equals(query)) {
                productList.add(jsonToProduct(productObject));
            }
        }

        return productList;
    }

    @Override
    public void checkIfFileExists() throws FileNotFoundException {
        super.checkIfFileExists();
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceJsonException {
        JsonArray products = readAllFromFile();
        List<Product> productList = new ArrayList<>();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            productList.add(jsonToProduct(productObject));
        }

        return productList;
    }
}