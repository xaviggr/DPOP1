package Bussines.Product;

public class ShopProduct {
    private double price;
    private Product product;

    public ShopProduct(Product product, Double price) {
        this.product = product;
        this.price = price;
    }

    public String getProductName() {
        return this.product.getProductName();
    }

    public Product getProduct() {
        return this.product;
    }

    public double getProductPrice() {
        return this.price;
    }

    public void setProductPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return this.product.getBrand();
    }

    public void setBrand(String brand) {
        this.product.setBrand(brand);
    }

    public Double getMaxPrice() {
        return this.product.getMaxPrice();
    }

    public void setMaxPrice(double maxPrice) {
        this.product.setMaxPrice(maxPrice);
    }
}
