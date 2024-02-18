package Persistence;

import Bussines.Product.ShopProduct;
import Bussines.Shop.Shop;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.util.List;

public interface ShopDAO {
    Shop getShop(String shopName) throws PersistenceJsonException, ApiException;
    List<Shop> getShops() throws PersistenceJsonException, ApiException;
    List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException, ApiException;
    List<String> getAllNameShops() throws PersistenceJsonException, ApiException;
    void addShop(Shop shop) throws PersistenceJsonException, ApiException;
    void updateShop(Shop shop) throws PersistenceJsonException, ApiException;
    List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException, ApiException;
    void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException, ApiException;
    void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException, ApiException;
    void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException, ApiException;
    void removeProductFromShops(String nameProduct) throws PersistenceJsonException, ApiException;
    List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException, ApiException;
    ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException, ApiException;
}
