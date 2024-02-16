package Bussines;

import Bussines.Product.ShopProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un carrito de compras en una tienda.
 */
public class ShopCart {

    private double TotalPrice;
    private List<ShopProduct> productList;
    /**
     * Constructor que inicializa un carrito de compras vacío.
     */
    public ShopCart() {
        this.TotalPrice = 0;
        this.productList = new ArrayList<>();
    }

    /**
     * Añade un producto al carrito y actualiza el precio total.
     *
     * @param product Producto a añadir al carrito.
     */
    public void addProductToCart(ShopProduct product) {
        productList.add(product);
        this.TotalPrice += product.getProductPrice();
    }

    /**
     * Limpia el carrito, eliminando todos los productos y reiniciando el precio total.
     */
    public void clearCart() {
        productList.clear();
        this.TotalPrice = 0;
    }

    /**
     * Obtiene el precio total actual del carrito.
     *
     * @return Precio total del carrito.
     */
    public double getTotalPrice() {
        return TotalPrice;
    }

    /**
     * Obtiene la lista de productos en el carrito.
     *
     * @return Lista de productos en el carrito.
     */
    public List<ShopProduct> getProductList() {
        return productList;
    }
}
