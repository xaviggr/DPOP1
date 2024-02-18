package Bussines.Product;

/**
 * Clase que representa un producto general.
 */
public class GeneralProduct extends Product {
    /**
     * Constructor para un producto general.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     */
    public GeneralProduct(String name, String brand, double maxPrice) {
        super(name, brand, maxPrice);
    }
}