package Bussines.Product;

/**
 * Clase que representa un producto con impuestos reducidos.
 */
public class ReducedTaxesProduct extends Product {
    /**
     * Constructor para un producto con impuestos reducidos.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     */
    public ReducedTaxesProduct(String name, String brand, double maxPrice) {
        super(name, brand, maxPrice);
    }
}
