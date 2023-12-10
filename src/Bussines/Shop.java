package Bussines;

import java.util.ArrayList;

/**
 * Clase que representa una tienda.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Shop {

    private String name;
    private String description;
    private int foundationYear;

    private String businessModel;

    private double earnings;

    private Catalog catalog;

    /**
     * Obtiene el nombre de la tienda.
     *
     * @return Nombre de la tienda.
     */
    public String getName() {
        return name;
    }
    /**
     * Obtiene la descripción de la tienda.
     *
     * @return Descripción de la tienda.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Obtiene el año de fundación de la tienda.
     *
     * @return Año de fundación de la tienda.
     */
    public int getFoundationYear() {
        return foundationYear;
    }
    /**
     * Obtiene el modelo de negocio de la tienda.
     *
     * @return Modelo de negocio de la tienda.
     */
    public String getBusinessModel() {
        return businessModel;
    }
    /**
     * Obtiene las ganancias de la tienda.
     *
     * @return Ganancias de la tienda.
     */
    public double getEarnings() {
        return earnings;
    }
    /**
     * Obtiene el catálogo de productos de la tienda.
     *
     * @return Catálogo de productos de la tienda.
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Constructor que inicializa una tienda con los parámetros proporcionados.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda.
     * @param foundationYear Año de fundación de la tienda.
     * @param earnings      Ganancias de la tienda.
     * @param businessModel Modelo de negocio de la tienda.
     * @param catalog       Catálogo de productos de la tienda.
     */
    public Shop(String name, String description, int foundationYear, double earnings, String businessModel, Catalog catalog) {
        this.name = name;
        this.description = description;
        this.foundationYear = foundationYear;
        this.businessModel = businessModel;
        this.earnings = earnings;
        if (catalog == null) {
            this.catalog = new Catalog(new ArrayList<>());
        } else {
            this.catalog = catalog;
        }
    }

    /**
     * Establece las ganancias de la tienda.
     *
     * @param earnings Ganancias de la tienda.
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
}
