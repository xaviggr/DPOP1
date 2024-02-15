package Bussines;

import Bussines.Product.ShopProduct;

import java.util.List;

public class Shop {
    private String name;
    private String description;
    private int foundationYear;
    private String businessModel;
    private double earnings;
    private List<ShopProduct> catalog;

    public Shop(String name, String description, int foundationYear, double earnings, String businessModel, List<ShopProduct> catalog) {
        this.name = name;
        this.description = description;
        this.foundationYear = foundationYear;
        this.businessModel = businessModel;
        this.earnings = earnings;
        this.catalog = catalog;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getFoundationYear() {
        return this.foundationYear;
    }

    public String getBusinessModel() {
        return this.businessModel;
    }

    public double getEarnings() {
        return this.earnings;
    }

    public List<ShopProduct> getCatalog() {
        return this.catalog;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public boolean removeProduct(String shopProductName) {
        for (ShopProduct shopProduct: catalog) {
            if (shopProduct.getProductName().equals(shopProductName)) {
                catalog.remove(shopProduct);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(ShopProduct shopProduct) {
        for (ShopProduct currentShopProduct: catalog) {
            if (currentShopProduct.getProductName().equals(shopProduct.getProductName())) {
                currentShopProduct.setBrand(shopProduct.getBrand());
                currentShopProduct.setMaxPrice(shopProduct.getMaxPrice());
                currentShopProduct.setProductPrice(shopProduct.getProductPrice());
                return true;
            }
        }
        return false;
    }

    public void addProduct(ShopProduct shopProduct) {
        this.catalog.add(shopProduct);
    }

    public boolean productExists(String productName) {
        for (ShopProduct shopProduct: catalog) {
            if (shopProduct.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}