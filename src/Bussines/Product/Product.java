package Bussines.Product;

import Bussines.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un producto.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Product {

    private String name;
    private String brand;
    private Double maxPrice;

    private List<Review> reviews = new ArrayList<>();

    private ProductCategory category;

    /**
     * Constructor que inicializa un nuevo producto con los detalles proporcionados.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     * @param category Categoría del producto.
     */
    public Product(String name, String brand, Double maxPrice, ProductCategory category) {
        this.name = name;
        this.brand = brand;
        this.maxPrice = maxPrice;
        this.category = category;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getProductName() {
        return name;
    }
    /**
     * Obtiene el precio máximo del producto.
     *
     * @return Precio máximo del producto.
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Establece el precio máximo del producto.
     *
     * @param maxPrice Nuevo precio máximo del producto.
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Obtiene la marca del producto.
     *
     * @return Marca del producto.
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Establece la marca del producto.
     *
     * @param brand Nueva marca del producto.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * Obtiene la categoría del producto.
     *
     * @return Categoría del producto.
     */
    public ProductCategory getCategory() {
        return category;
    }
    /**
     * Establece la categoría del producto.
     *
     * @param category Nueva categoría del producto.
     */
    public void setCategory(ProductCategory category) {
        this.category = category;
    }
    /**
     * Obtiene la lista de revisiones del producto.
     *
     * @return Lista de revisiones del producto.
     */
    public List<Review> getReviews() {
        return reviews;
    }
    /**
     * Agrega una nueva revisión al producto.
     *
     * @param review Nueva revisión a agregar.
     */
    public void addReview(Review review) {
        reviews.add(review);
    }
    /**
     * Elimina una revisión del producto.
     *
     * @param review Revisión a eliminar.
     */
    public void removeReview(Review review) {
        reviews.remove(review);
    }

}
