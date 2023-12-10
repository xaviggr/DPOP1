package Bussines;

import java.util.ArrayList;

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
        if (catalog == null) {
            this.catalog = new Catalog(new ArrayList<>());
        } else {
            this.catalog = catalog;
        }
    }

    public void setEarnings(double v) {
        this.earnings = v;
    }
}
