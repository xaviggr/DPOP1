package Bussines;

import Bussines.Product.ShopProduct;

import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class Catalog {
    private List<ShopProduct> shopProducts;

    public Catalog(List<ShopProduct> shopProducts) {
        this.shopProducts = shopProducts;
    }

    public List<ShopProduct> getProducts() {
        return shopProducts;
    }
}
