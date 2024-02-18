package Persistence;

import Bussines.Product.ShopProduct;
import Bussines.Shop.Shop;
import Persistence.exception.PersistenceJsonException;

import java.util.List;

public class ShopDAOApi implements ShopDAO{
    @Override
    public Shop getShop(String shopName) throws PersistenceJsonException {
        return null;
    }

    @Override
    public List<Shop> getShops() throws PersistenceJsonException {
        return null;
    }

    @Override
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException {
        return null;
    }

    @Override
    public List<String> getAllNameShops() throws PersistenceJsonException {
        return null;
    }

    @Override
    public void addShop(Shop shop) throws PersistenceJsonException {

    }

    @Override
    public void updateShop(Shop shop) throws PersistenceJsonException {

    }

    @Override
    public Shop findShopByName(List<Shop> shops, String shopName) {
        return null;
    }

    @Override
    public List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException {
        return null;
    }

    @Override
    public void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {

    }

    @Override
    public void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException {

    }

    @Override
    public void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException {

    }

    @Override
    public void removeProductFromShops(String nameProduct) throws PersistenceJsonException {

    }

    @Override
    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException {
        return null;
    }

    @Override
    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException {
        return null;
    }
}
