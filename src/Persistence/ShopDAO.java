package Persistence;

import Bussines.Product.Product;
import Bussines.Product.ShopProduct;
import Bussines.Shop;

import java.util.List;

public class ShopDAO extends DAOJSON {
    public ShopDAO() {
        super();
    }

    public Shop getShop(String shopName) {
        return null;
    }

    public List<Shop> getShops() {
        return null;
    }

    public List<Shop> getShopsWhereProductExistsInCatalog(ShopProduct shopProduct) {
        return null;
    }

    public List<Product> getProductsFromShop(String shopName) {
        return null;
    }

    public Product getProductFromShop(String shopName, String productName) {
        return null;
    }

    public boolean addProductInShop(String shopName, ShopProduct shopProduct) {
        return false;
    }

    public boolean updateProductFromShop(String shopName, ShopProduct shopProduct) {
        return false;
    }

    public boolean removeProductFromShop(String shopName, ShopProduct shopProduct) {
        return false;
    }
}
