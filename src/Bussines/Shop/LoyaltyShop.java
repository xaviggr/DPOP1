package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class LoyaltyShop extends Shop {

    private double threshold;
    public LoyaltyShop(String name, String description, int foundationYear, double earnings,  List<ShopProduct> catalog,double threshold) {
        super(name, description, foundationYear, earnings, catalog);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }
}
