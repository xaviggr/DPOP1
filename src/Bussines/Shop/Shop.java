package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

/**
 * Clase abstracta que representa una tienda.
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class Shop {
    private String name;
    private String description;
    private int foundationYear;
    private double earnings;
    private List<ShopProduct> catalog;
    /**
     * Constructor para una tienda.
     *
     * @param name           Nombre de la tienda.
     * @param description    Descripción de la tienda.
     * @param foundationYear Año de fundación de la tienda.
     * @param earnings       Ganancias de la tienda.
     * @param catalog        Catálogo de productos de la tienda.
     */
    public Shop(String name, String description, int foundationYear, double earnings, List<ShopProduct> catalog) {
        this.name = name;
        this.description = description;
        this.foundationYear = foundationYear;
        this.earnings = earnings;
        this.catalog = catalog;
    }
    /**
     * Obtiene el nombre de la tienda.
     *
     * @return Nombre de la tienda.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Obtiene la descripción de la tienda.
     *
     * @return Descripción de la tienda.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Obtiene el año de fundación de la tienda.
     *
     * @return Año de fundación de la tienda.
     */
    public int getFoundationYear() {
        return this.foundationYear;
    }
    /**
     * Obtiene el modelo de negocio de la tienda.
     *
     * @return Modelo de negocio de la tienda.
     */
    public String getBusinessModel() {
        return this.getClass().getSimpleName();
    }
    /**
     * Obtiene las ganancias de la tienda.
     *
     * @return Ganancias de la tienda.
     */
    public double getEarnings() {
        return this.earnings;
    }
    /**
     * Obtiene el catálogo de productos de la tienda.
     *
     * @return Catálogo de productos de la tienda.
     */
    public List<ShopProduct> getCatalog() {
        return this.catalog;
    }
/**
     * Establece las ganancias de la tienda.
     *
     * @param earnings Ganancias de la tienda.
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
    /**
     * Elimina un producto del catálogo de la tienda.
     *
     * @param shopProductName Nombre del producto en la tienda.
     * @return true si se eliminó con éxito, false si no se encontró el producto.
     */
    public boolean removeProduct(String shopProductName) {
        for (ShopProduct shopProduct: catalog) {
            if (shopProduct.getProductName().equals(shopProductName)) {
                catalog.remove(shopProduct);
                return true;
            }
        }
        return false;
    }
    /**
     * Actualiza la información de un producto en el catálogo de la tienda.
     *
     * @param shopProduct Nuevo producto con la información actualizada.
     * @return true si se actualizó con éxito, false si no se encontró el producto.
     */
    public boolean updateProduct(ShopProduct shopProduct) {
        for (ShopProduct currentShopProduct: catalog) {
            if (currentShopProduct.getProductName().equals(shopProduct.getProductName())) {
                currentShopProduct.setBrand(shopProduct.getBrand());
                currentShopProduct.setMaxPrice(shopProduct.getMaxPrice());
                currentShopProduct.setProductPrice(shopProduct.getProductPrice());
                return true;
            }
        }
        return false;
    }
    /**
     * Agrega un nuevo producto al catálogo de la tienda.
     *
     * @param shopProduct Nuevo producto a agregar.
     */
    public void addProduct(ShopProduct shopProduct) {
        this.catalog.add(shopProduct);
    }
    /**
     * Verifica si un producto existe en el catálogo de la tienda.
     *
     * @param productName Nombre del producto a verificar.
     * @return true si el producto existe, false si no.
     */
    public boolean productExists(String productName) {
        for (ShopProduct shopProduct: catalog) {
            if (shopProduct.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}