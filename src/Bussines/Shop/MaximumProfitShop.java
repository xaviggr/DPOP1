package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

/**
 * Clase que representa una tienda de máximo beneficio.
 */
@SuppressWarnings("SpellCheckingInspection")
public class MaximumProfitShop extends Shop {
     /**
     * Constructor para una tienda de máximo beneficio.
     *
     * @param name           Nombre de la tienda.
     * @param description    Descripción de la tienda.
     * @param foundationYear Año de fundación de la tienda.
     * @param earnings       Ganancias de la tienda.
     * @param catalog        Catálogo de productos de la tienda.
     */
    public MaximumProfitShop(String name, String description, int foundationYear, double earnings, List<ShopProduct> catalog) {
        super(name, description, foundationYear, earnings, catalog);
    }
}
