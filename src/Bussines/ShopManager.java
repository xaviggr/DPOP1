package Bussines;

import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Bussines.Product.Product;

import java.io.FileNotFoundException;
import java.util.List;

public class ShopManager {

    private ShopDAO shopDAO;
    private ProductDAO productDAO;

    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
    }

    public void createProduct(String name, String brand, double maxPrice, ProductCategory category) {
        Product product = new Product(name, brand, maxPrice, category);
        productDAO.addProduct(product);
    }

    public void removeProduct(String nameProduct) {
        productDAO.removeProduct(nameProduct);
        shopDAO.removeProductFromShops(nameProduct);
    }

    public void createShop(Shop shop) {
        shopDAO.addShop(shop);
    }

    public void expandCatalog(String shopName, ShopProduct sp) {
        shopDAO.addProductInShop(shopName,sp);
    }

    public void reduceCatalog(String shopName, String productName) {
        shopDAO.removeProductFromShop(shopName,productName);
    }

    public Product findProduct(String nameProduct) {
        return  productDAO.findProduct(nameProduct);
    }

    public List<Product> searchProductsByQuery(String query) {
        return productDAO.findProductsByQuery(query);
    }

    public List<Review> readReviews(String nameProduct) {
        return productDAO.findProduct(nameProduct).getReviews();
    }

    public void makeReview(String nameProduct, Review review) {
        Product product = productDAO.findProduct(nameProduct);
        product.addReview(review);
        productDAO.updateProduct(product);
    }

    public void checkIfFileExists() throws FileNotFoundException {
        productDAO.checkIfFileExists();
    }

    //New function
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // New function
    public List<ShopProduct> getAllProductsFromShop(String shopName) {
        return shopDAO.getProductsFromShop(shopName);
    }

    //New function
    public List<Shop> getAllShops() {
        return shopDAO.getShops();
    }

    public Shop findShopByName(String shopName) {
        List<Shop> list = shopDAO.getShops();
         return shopDAO.findShopByName(list,shopName);
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) {
        return shopDAO.getShopsWhereProductExistsInCatalog(productName);
    }

    public ShopProduct getProductFromShop(String shopName,String productName) {
        return shopDAO.getProductFromShop(shopName,productName);
    }

    public List<String> getAllNameShops() {
        return shopDAO.getAllNameShops();
    }

    public Shop getShop(String shopName) {
        return shopDAO.getShop(shopName);
    }

    public List<String> getAllProductsNameFromShop(String shopName) {
        return shopDAO.getAllProductsNameFromShop(shopName);
    }

    public void checkout(Shop s) {
        shopDAO.updateShop(s);
    }
}
