package Bussines;

import Bussines.Product.*;
import Bussines.Shop.Shop;
import Bussines.exception.PersistenceIntegrationException;
import Persistence.*;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.List;

public class ShopManager {
    private  ShopDAO shopDAO;
    private  ProductDAO productDAO;

    public ShopManager(DataSourceOptions options) {
        switch (options) {
            case API -> {
                this.shopDAO = new ShopDAOApi();
                this.productDAO = new ProductDAOApi();
            }
            case JSON -> {
                this.shopDAO = new ShopDAOJSON();
                this.productDAO = new ProductDAOJSON();
            }
        }
    }

    public void createProduct(String name, String brand, double maxPrice, String category) throws PersistenceIntegrationException {
        try {
            Product p;

            switch (category) {
                case "REDUCED_TAXES" -> p = new ReducedTaxesProduct(name, brand, maxPrice);
                case "SUPER_REDUCED_TAXES" -> p = new SuperReducedTaxes(name, brand, maxPrice);
                default -> p = new GeneralProduct(name, brand, maxPrice);
            }
            this.productDAO.addProduct(p);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void removeProduct(String nameProduct) throws PersistenceIntegrationException {
        try {
            this.productDAO.removeProduct(nameProduct);
            this.shopDAO.removeProductFromShops(nameProduct);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void createShop(Shop shop) throws PersistenceIntegrationException {
        try {
            this.shopDAO.addShop(shop);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void expandCatalog(String shopName, ShopProduct sp) throws PersistenceIntegrationException {
        try {
            this.shopDAO.addProductInShop(shopName, sp);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void reduceCatalog(String shopName, String productName) throws PersistenceIntegrationException {
        try {
            this.shopDAO.removeProductFromShop(shopName, productName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public Product findProduct(String nameProduct) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProduct(nameProduct);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<Product> searchProductsByQuery(String query) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProductsByQuery(query);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<Review> readReviews(String nameProduct) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProduct(nameProduct).getReviews();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void makeReview(String nameProduct, Review review) throws PersistenceIntegrationException {
        try {
            Product product;
            product = this.productDAO.findProduct(nameProduct);
            product.addReview(review);
            this.productDAO.updateProduct(product);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void checkIfFileExists() throws RuntimeException {
        try {
            this.productDAO.checkIfFileExists();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() throws PersistenceIntegrationException {
        try {
            return this.productDAO.getAllProducts();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<ShopProduct> getAllProductsFromShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getProductsFromShop(shopName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<Shop> getAllShops() throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShops();
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public Shop findShopByName(String shopName) throws PersistenceIntegrationException {
        try {
            List<Shop> list;
            list = this.shopDAO.getShops();
            return this.shopDAO.findShopByName(list, shopName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShopsWhereProductExistsInCatalog(productName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getProductFromShop(shopName, productName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<String> getAllNameShops() throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getAllNameShops();
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public Shop getShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShop(shopName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getAllProductsNameFromShop(shopName);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }

    public void checkout(Shop s) throws PersistenceIntegrationException {
        try {
            this.shopDAO.updateShop(s);
        } catch (PersistenceJsonException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
}