package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class LoyalityShop extends Shop {

    public LoyalityShop(String name, String description, int foundationYear, double earnings,  List<ShopProduct> catalog) {
        super(name, description, foundationYear, earnings, catalog);
    }

}
