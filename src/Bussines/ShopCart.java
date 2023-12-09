package Bussines;

import Bussines.Product.ShopProduct;

import java.util.ArrayList;
import java.util.List;

public class ShopCart {

    private double TotalPrice;
    private List<ShopProduct> productList;
    public ShopCart(double totalPrice) {
        this.TotalPrice = totalPrice;
        this.productList = new ArrayList<>();
    }

    public void addProductToCart(ShopProduct product) {
        productList.add(product);
        this.TotalPrice += product.getProductPrice();
    }

    public double calculateTotalPrice() {
        return TotalPrice;
    }

}
