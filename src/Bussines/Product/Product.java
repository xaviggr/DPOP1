package Bussines.Product;

import Bussines.Review;

import java.util.List;

public class Product {

    private String name;
    private String brand;
    private Double maxPrice;

    private List<Review> reviews;

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

    //New function
    public String getProductBrand() {
        return brand;
    }


    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }
}
