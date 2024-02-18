package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

/**
 * Clase que representa un tipo de tienda con descuento a socios.
 */
@SuppressWarnings("SpellCheckingInspection")
public class LoyaltyShop extends Shop {

    private double threshold;
    /**
     * Constructor para una tienda.
     *
     * @param name           Nombre de la tienda.
     * @param description    Descripción de la tienda.
     * @param foundationYear Año de fundación de la tienda.
     * @param earnings       Ganancias de la tienda.
     * @param catalog        Catálogo de productos de la tienda.
     * @param threshold      Umbral de descuento de la tienda.
     */
    public LoyaltyShop(String name, String description, int foundationYear, double earnings,  List<ShopProduct> catalog,double threshold) {
        super(name, description, foundationYear, earnings, catalog);
        this.threshold = threshold;
    }

    /**
     * Obtiene el umbral de descuento de la tienda.
     *
     * @return Umbral de lealtad.
     */
    public double getThreshold() {
        return threshold;
    }
}
