package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class SponsoredShop extends Shop {

    private String brand;

    public SponsoredShop(String name, String description, int foundationYear, double earnings, List<ShopProduct> catalog,String brand) {
        super(name, description, foundationYear, earnings, catalog);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
