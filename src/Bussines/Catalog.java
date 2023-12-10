package Bussines;

import Bussines.Product.ShopProduct;

import java.util.List;

/**
 * Clase que representa un catálogo de productos de una tienda.
 */
@SuppressWarnings({"FieldMayBeFinal", "SpellCheckingInspection"})
public class Catalog {
    /**
     * Lista de productos en el catálogo.
     */
    private List<ShopProduct> shopProducts;

    /**
     * Constructor que inicializa el catálogo con la lista de productos proporcionada.
     *
     * @param shopProducts Lista de productos en el catálogo.
     */
    public Catalog(List<ShopProduct> shopProducts) {
        this.shopProducts = shopProducts;
    }

    /**
     * Obtiene la lista de productos en el catálogo.
     *
     * @return Lista de productos en el catálogo.
     */
    public List<ShopProduct> getProducts() {
        return shopProducts;
    }

    /**
     * Elimina un producto del catálogo por nombre.
     *
     * @param shopProductName Nombre del producto a eliminar.
     * @return `true` si se eliminó con éxito, `false` si no se encontró el producto.
     */
    public boolean removeProduct(String shopProductName) {
        for (ShopProduct shopProduct: shopProducts) {
            if (shopProduct.getProductName().equals(shopProductName)) {
                shopProducts.remove(shopProduct);
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza la información de un producto en el catálogo.
     *
     * @param shopProduct Nuevo estado del producto.
     * @return `true` si se actualizó con éxito, `false` si no se encontró el producto.
     */
    public boolean updateProduct(ShopProduct shopProduct) {
        for (ShopProduct currentShopProduct: shopProducts) {
            if (currentShopProduct.getProductName().equals(shopProduct.getProductName())) {
                currentShopProduct.setBrand(shopProduct.getBrand());
                currentShopProduct.setCategory(shopProduct.getCategory());
                currentShopProduct.setMaxPrice(shopProduct.getMaxPrice());
                currentShopProduct.setProductPrice(shopProduct.getProductPrice());
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo producto al catálogo.
     *
     * @param shopProduct Nuevo producto a agregar.
     */
    public void addProduct(ShopProduct shopProduct) {
        shopProducts.add(shopProduct);
    }

    /**
     * Verifica si un producto existe en el catálogo por nombre.
     *
     * @param productName Nombre del producto a verificar.
     * @return `true` si el producto existe, `false` si no.
     */
    public boolean productExists(String productName) {
        for (ShopProduct shopProduct: shopProducts) {
            if (shopProduct.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
