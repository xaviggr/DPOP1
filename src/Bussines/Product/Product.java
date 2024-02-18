package Bussines.Product;

import Bussines.Review;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un producto.
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class Product {
    private String name;
    private String brand;
    private Double maxPrice;
    private List<Review> reviews = new ArrayList<>();
    protected double taxes;

    /**
     * Constructor para un producto.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     */
    public Product(String name, String brand, Double maxPrice) {
        this.name = name;
        this.brand = brand;
        this.maxPrice = maxPrice;
    }
    /**
     * Obtiene la categoría del producto.
     *
     * @return Categoría del producto.
     */
    public String getCategory() {
        return this.getClass().getSimpleName();
    }
    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getProductName() {
        return this.name;
    }
    /**
     * Obtiene el precio máximo del producto.
     *
     * @return Precio máximo del producto.
     */
    public double getMaxPrice() {
        return this.maxPrice;
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
        return this.brand;
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
     * Obtiene la lista de revisiones del producto.
     *
     * @return Lista de revisiones del producto.
     */
    public List<Review> getReviews() {
        return this.reviews;
    }
    /**
     * Agrega una revisión al producto.
     *
     * @param review Revisión a agregar.
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    /**
     * Elimina una revisión del producto.
     *
     * @param review Revisión a eliminar.
     */
    public void removeReview(Review review) {
        this.reviews.remove(review);
    }
    /**
     * Obtiene las tasas del producto.
     *
     * @return Tasas del producto.
     */
    public double getTaxes() {
        return taxes;
    }
}
