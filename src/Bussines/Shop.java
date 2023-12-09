package Bussines;

public class Shop {

    private String name;
    private String description;
    private int foundationYear;

    private String businessModel;

    private Catalog catalog;

    public Shop(String name, String description, int foundationYear, String businessModel) {
        this.name = name;
        this.description = description;
        this.foundationYear = foundationYear;
        this.businessModel = businessModel;
        this.catalog = new Catalog(null);
    }
}
