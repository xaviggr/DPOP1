package Persistence;
import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Review;
import Persistence.exception.PersistenceJsonException;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa operaciones de lectura y escritura específicas para productos en formato JSON.
 * Extiende la clase abstracta DAOJSON y utiliza Gson para la serialización y deserialización de datos.
 *
 * @see DAOJSON
 * @see com.google.gson.Gson
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SpellCheckingInspection"})
public class ProductDAO extends DAOJSON {

    /**
     * Constructor que establece la ruta del archivo JSON asociado a los productos.
     */
    public ProductDAO() {
        this.path += "products.json";
    }

    /**
     * Convierte un objeto Product a un JsonObject para su representación en formato JSON.
     *
     * @param product El producto a convertir.
     * @return Un JsonObject que representa el producto en formato JSON.
     */
    private JsonObject productToJson(Product product) {
        JsonObject productJson = new JsonObject();
        productJson.addProperty("name", product.getProductName());
        productJson.addProperty("brand", product.getBrand());
        productJson.addProperty("mrp", product.getMaxPrice());
        productJson.addProperty("category", product.getCategory().toString());
        productJson.add("reviews", reviewsToJsonArray(product.getReviews()));
        return productJson;
    }

    /**
     * Convierte una lista de objetos Review a un JsonArray para su representación en formato JSON.
     *
     * @param reviews La lista de revisiones a convertir.
     * @return Un JsonArray que representa las revisiones en formato JSON.
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
     * Convierte un JsonObject a un objeto Product después de leerlo desde el formato JSON.
     *
     * @param productObject El JsonObject que representa el producto en formato JSON.
     * @return Un objeto Product creado a partir del JsonObject.
     */
    private Product jsonToProduct(JsonObject productObject) {
        String name = productObject.get("name").getAsString();
        String brand = productObject.get("brand").getAsString();
        Double mrp = productObject.get("mrp").getAsDouble();
        String category = productObject.get("category").getAsString();

        Product product = new Product(name, brand, mrp, ProductCategory.valueOf(category));

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

    /**
     *
     *
     */
    public void addProduct(Product product) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();
        JsonObject newProduct = productToJson(product);
        products.add(newProduct);
        saveToFile(products);
    }

    /**
     *
     *
     */
    public void updateProduct(Product product) throws PersistenceJsonException {
        JsonArray products = readAllFromFile();

        for (JsonElement productElement : products) {
            JsonObject productObject = productElement.getAsJsonObject();
            if (productObject.get("name").getAsString().equals(product.getProductName())) {
                productObject.addProperty("brand", product.getBrand());
                productObject.addProperty("mrp", product.getMaxPrice());
                productObject.addProperty("category", product.getCategory().toString());
                productObject.add("reviews", reviewsToJsonArray(product.getReviews()));
                saveToFile(products);
                return;
            }
        }
    }

    /**
     *
     *
     */
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

    /**
     * Busca un producto por su nombre en el archivo JSON.
     *
     * @param productName El nombre del producto a buscar.
     * @return El objeto Product encontrado, o null si no se encontró ningún producto con ese nombre.
     */
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

    /**
     * Busca productos que coincidan con la consulta en el archivo JSON.
     *
     * @param query La consulta para buscar productos.
     * @return Una lista de productos que coinciden con la consulta.
     */
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

    /**
     * Obtiene todos los productos almacenados en el archivo JSON.
     *
     * @return Una lista que contiene todos los productos.
     */
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
