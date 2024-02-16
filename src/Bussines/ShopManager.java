package Bussines;

import Bussines.Product.GeneralProduct;
import Bussines.Product.Product;
import Bussines.Product.ReducedTaxesProduct;
import Bussines.Product.ShopProduct;
import Bussines.Product.SuperReducedTaxes;
import Bussines.Shop.Shop;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Persistence.exception.PersistenceJsonException;
import java.io.FileNotFoundException;
import java.util.List;

public class ShopManager {
    private final ShopDAO shopDAO;
    private final ProductDAO productDAO;

    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
    }

    public void createProduct(String name, String brand, double maxPrice, String category) throws PersistenceJsonException {
        Product p;

        switch (category) {
            case "REDUCED_TAXES" -> p = new ReducedTaxesProduct(name, brand, maxPrice);
            case "SUPER_REDUCED_TAXES" -> p = new SuperReducedTaxes(name, brand, maxPrice);
            default -> p = new GeneralProduct(name, brand, maxPrice);
        }

        this.productDAO.addProduct(p);
    }

    public void removeProduct(String nameProduct) throws PersistenceJsonException {
        this.productDAO.removeProduct(nameProduct);
        this.shopDAO.removeProductFromShops(nameProduct);
    }

    public void createShop(Shop shop) throws PersistenceJsonException {
        this.shopDAO.addShop(shop);
    }

    public void expandCatalog(String shopName, ShopProduct sp) throws PersistenceJsonException {
        this.shopDAO.addProductInShop(shopName, sp);
    }

    public void reduceCatalog(String shopName, String productName) throws PersistenceJsonException {
        this.shopDAO.removeProductFromShop(shopName, productName);
    }

    public Product findProduct(String nameProduct) throws PersistenceJsonException {
        return this.productDAO.findProduct(nameProduct);
    }

    public List<Product> searchProductsByQuery(String query) throws PersistenceJsonException {
        return this.productDAO.findProductsByQuery(query);
    }

    public List<Review> readReviews(String nameProduct) throws PersistenceJsonException {
        return this.productDAO.findProduct(nameProduct).getReviews();
    }

    public void makeReview(String nameProduct, Review review) throws PersistenceJsonException {
        Product product = this.productDAO.findProduct(nameProduct);
        product.addReview(review);
        this.productDAO.updateProduct(product);
    }

    public void checkIfFileExists() throws FileNotFoundException {
        this.productDAO.checkIfFileExists();
    }

    public List<Product> getAllProducts() throws PersistenceJsonException {
        return this.productDAO.getAllProducts();
    }

    public List<ShopProduct> getAllProductsFromShop(String shopName) throws PersistenceJsonException {
        return this.shopDAO.getProductsFromShop(shopName);
    }

    public List<Shop> getAllShops() throws PersistenceJsonException {
        return this.shopDAO.getShops();
    }

    public Shop findShopByName(String shopName) throws PersistenceJsonException {
        List<Shop> list = this.shopDAO.getShops();
        return this.shopDAO.findShopByName(list, shopName);
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException {
        return this.shopDAO.getShopsWhereProductExistsInCatalog(productName);
    }

    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException {
        return this.shopDAO.getProductFromShop(shopName, productName);
    }

    public List<String> getAllNameShops() throws PersistenceJsonException {
        return this.shopDAO.getAllNameShops();
    }

    public Shop getShop(String shopName) throws PersistenceJsonException {
        return this.shopDAO.getShop(shopName);
    }

    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException {
        return this.shopDAO.getAllProductsNameFromShop(shopName);
    }

    public void checkout(Shop s) throws PersistenceJsonException {
        this.shopDAO.updateShop(s);
    }
}