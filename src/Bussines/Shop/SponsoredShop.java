package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class SponsoredShop extends Shop {
    public SponsoredShop(String name, String description, int foundationYear, double earnings, List<ShopProduct> catalog) {
        super(name, description, foundationYear, earnings, catalog);
    }
}
