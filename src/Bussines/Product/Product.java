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

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ProductCategory getCategory() {
        return category;
    }
    public void setCategory(ProductCategory category) {
        this.category = category;
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
