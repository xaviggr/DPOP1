package Bussines;

import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Bussines.Product.Product;

import java.io.FileNotFoundException;

public class ShopManager {

    private ShopDAO shopDAO;
    private ProductDAO productDAO;

    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
    }

    public void createProduct(String name, String brand, double maxPrice, ProductCategory category) {
        Product product = new Product(name, brand, maxPrice, category);
    }

    public void removeProduct(String nameProduct) {

    }

    public void createShop(Shop shop) {

    }

    public void expandCatalog(String shopName, String productName, double price) {

    }

    public void reduceCatalog(String shopName) {

    }

    public void findProduct(String nameProduct) {
        productDAO.findProduct(nameProduct);
    }

    public void readReviews(String nameProduct) {

    }

    public void makeReview(String nameProduct, Review review) {

    }

    public void addToCart(ShopProduct shopProduct) {

    }

    public void checkout() {

    }

    public void clearCart() {

    }

    public void checkIfFileExists() throws FileNotFoundException {
        productDAO.checkIfFileExists();
    }
}
