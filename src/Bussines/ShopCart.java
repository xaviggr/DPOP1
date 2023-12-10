package Bussines;

import Bussines.Product.ShopProduct;

import java.util.ArrayList;
import java.util.List;

public class ShopCart {

    private double TotalPrice;
    private List<ShopProduct> productList;
    public ShopCart() {
        this.TotalPrice = 0;
        this.productList = new ArrayList<>();
    }

    public void addProductToCart(ShopProduct product) {
        productList.add(product);
        this.TotalPrice += product.getProductPrice();
    }
    public void clearCart() {
        productList.clear();
        this.TotalPrice = 0;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public List<ShopProduct> getProductList() {
        return productList;
    }
}
