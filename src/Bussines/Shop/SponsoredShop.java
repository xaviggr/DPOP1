package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class SponsoredShop extends Shop {
    public SponsoredShop(String name, String description, int foundationYear, double earnings, String businessModel, List<ShopProduct> catalog) {
        super(name, description, foundationYear, earnings, businessModel, catalog);
    }
}
