package Bussines.Shop;

import Bussines.Product.ShopProduct;

import java.util.List;

public class Sponsored extends Shop {
    public Sponsored(String name, String description, int foundationYear, double earnings, String businessModel, List<ShopProduct> catalog) {
        super(name, description, foundationYear, earnings, businessModel, catalog);
    }
}
