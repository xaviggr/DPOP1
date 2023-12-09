package Bussines.Product;

import Bussines.Review;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private String brand;
    private Double maxPrice;

    private List<Review> reviews = new ArrayList<>();

    private ProductCategory category;

    public Product(String name, String brand, Double maxPrice, ProductCategory category) {
        this.name = name;
        this.brand = brand;
        this.maxPrice = maxPrice;
        this.category = category;
    }

    public String getProductName() {
        return name;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getBrand() {
        return brand;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

}
