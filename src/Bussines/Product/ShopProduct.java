package Bussines.Product;

import Bussines.ShopCart;
import jdk.jfr.Category;

import java.util.List;

public class ShopProduct extends Product {

    private double price;

    public ShopProduct(String name, String brand, Double maxPrice, ProductCategory category, Double price) {
        super(name, brand, maxPrice, category);
        this.price = price;
    }

    public double getProductPrice() {
        return price;
    }

    public void setProductPrice(double price) {
        this.price = price;
    }
}
