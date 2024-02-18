package Bussines.Product;
/**
 * Clase que representa un producto con impuestos súper reducidos.
 */
public class SuperReducedTaxes extends Product {
    /**
     * Constructor para un producto con impuestos súper reducidos.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     */
    public SuperReducedTaxes(String name, String brand, double maxPrice) {
        super(name, brand, maxPrice);
    }
}
