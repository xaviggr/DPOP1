package Bussines.Product;

/**
 * Clase que representa un producto en una tienda con su respectivo precio.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ShopProduct {
    private double price;
    private Product product;
    /**
     * Constructor para un producto en una tienda con su respectivo precio.
     *
     * @param product Producto asociado.
     * @param price   Precio del producto en la tienda.
     */
    public ShopProduct(Product product, Double price) {
        this.product = product;
        this.price = price;
    }
    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getProductName() {
        return this.product.getProductName();
    }
    /**
     * Obtiene el producto asociado.
     *
     * @return Producto asociado.
     */
    public Product getProduct() {
        return this.product;
    }
    /**
     * Obtiene el precio del producto en la tienda.
     *
     * @return Precio del producto en la tienda.
     */
    public double getProductPrice() {
        return this.price;
    }
    /**
     * Establece el precio del producto en la tienda.
     *
     * @param price Nuevo precio del producto en la tienda.
     */
    public void setProductPrice(double price) {
        this.price = price;
    }
    /**
     * Obtiene la marca del producto.
     *
     * @return Marca del producto.
     */
    public String getBrand() {
        return this.product.getBrand();
    }
    /**
     * Establece la marca del producto.
     *
     * @param brand Nueva marca del producto.
     */
    public void setBrand(String brand) {
        this.product.setBrand(brand);
    }
    /**
     * Obtiene el precio máximo del producto.
     *
     * @return Precio máximo del producto.
     */
    public Double getMaxPrice() {
        return this.product.getMaxPrice();
    }
    /**
     * Establece el precio máximo del producto.
     *
     * @param maxPrice Nuevo precio máximo del producto.
     */
    public void setMaxPrice(double maxPrice) {
        this.product.setMaxPrice(maxPrice);
    }
}
