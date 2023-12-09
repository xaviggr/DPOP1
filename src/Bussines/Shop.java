package Bussines;

public class Shop {

    private String name;
    private String description;
    private int foundationYear;

    private String businessModel;

    private double earnings;

    private Catalog catalog;

    public Shop(String name, String description, int foundationYear, double earnings, String businessModel, Catalog catalog) {
        this.name = name;
        this.description = description;
        this.foundationYear = foundationYear;
        this.businessModel = businessModel;
        this.earnings = earnings;
        this.catalog = catalog;
    }
}
