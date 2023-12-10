package Bussines.Product;


/**
 * Clase que representa un producto específico en el catálogo de una tienda.
 * Hereda de la clase base {@link Product}.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ShopProduct extends Product {

    private double price;

    /**
     * Constructor para crear un objeto ShopProduct.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo sugerido del producto.
     * @param category Categoría del producto.
     * @param price    Precio del producto en la tienda.
     */
    public ShopProduct(String name, String brand, Double maxPrice, ProductCategory category, Double price) {
        super(name, brand, maxPrice, category);
        this.price = price;
    }

    /**
     * Obtiene el precio del producto en la tienda.
     *
     * @return Precio del producto.
     */
    public double getProductPrice() {
        return price;
    }

    /**
     * Establece el precio del producto en la tienda.
     *
     * @param price Nuevo precio del producto.
     */
    public void setProductPrice(double price) {
        this.price = price;
    }
}
