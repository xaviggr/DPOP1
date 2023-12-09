package Bussines;

public class Shop {

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public double getEarnings() {
        return earnings;
    }

    public Catalog getCatalog() {
        return catalog;
    }

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
