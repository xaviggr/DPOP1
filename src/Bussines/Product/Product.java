package Bussines.Product;

import Bussines.Review;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    private String name;
    private String brand;
    private Double maxPrice;
    private List<Review> reviews = new ArrayList<>();

    public Product(String name, String brand, Double maxPrice) {
        this.name = name;
        this.brand = brand;
        this.maxPrice = maxPrice;
    }

    public String getCategory() {
        return this.getClass().getSimpleName();
    }

    public String getProductName() {
        return this.name;
    }

    public double getMaxPrice() {
        return this.maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public double calcPrice(double price, int iva) {
        return price/(1 + ((double) iva /100));
    }
}
