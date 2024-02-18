package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

/**
 * Clase que representa una tienda patrocinada.
 */
@SuppressWarnings("SpellCheckingInspection")
public class SponsoredShop extends Shop {
    private String brand;
    /**
     * Constructor para una tienda patrocinada.
     *
     * @param name           Nombre de la tienda.
     * @param description    Descripción de la tienda.
     * @param foundationYear Año de fundación de la tienda.
     * @param earnings       Ganancias de la tienda.
     * @param catalog        Catálogo de productos de la tienda.
     * @param brand          Marca patrocinadora.
     */
    public SponsoredShop(String name, String description, int foundationYear, double earnings, List<ShopProduct> catalog,String brand) {
        super(name, description, foundationYear, earnings, catalog);
        this.brand = brand;
    }
    /**
     * Obtiene la marca patrocinadora de la tienda.
     *
     * @return Marca patrocinadora.
     */
    public String getBrand() {
        return brand;
    }
}
