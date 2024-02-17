package Persistence;

import Bussines.Product.ShopProduct;
import Bussines.Shop.Shop;
import Persistence.exception.PersistenceJsonException;

import java.util.List;

public interface ShopDAOi {
    Shop getShop(String shopName) throws PersistenceJsonException;
    List<Shop> getShops() throws PersistenceJsonException;
    List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException;
    List<String> getAllNameShops() throws PersistenceJsonException;
    void addShop(Shop shop) throws PersistenceJsonException;
    void updateShop(Shop shop) throws PersistenceJsonException;
    Shop findShopByName(List<Shop> shops, String shopName);
    List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException;
    void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException;
    void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException;
    void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException;
    void removeProductFromShops(String nameProduct) throws PersistenceJsonException;
    List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException;
    ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException;
}
