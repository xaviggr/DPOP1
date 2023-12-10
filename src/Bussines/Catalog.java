package Bussines;

import Bussines.Product.ShopProduct;

import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class Catalog {
    private List<ShopProduct> shopProducts;

    public Catalog(List<ShopProduct> shopProducts) {
        this.shopProducts = shopProducts;
    }

    public List<ShopProduct> getProducts() {
        return shopProducts;
    }

    public boolean removeProduct(String shopProductName) {
        for (ShopProduct shopProduct: shopProducts) {
            if (shopProduct.getProductName().equals(shopProductName)) {
                shopProducts.remove(shopProduct);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(ShopProduct shopProduct) {
        for (ShopProduct currentShopProduct: shopProducts) {
            if (currentShopProduct.getProductName().equals(shopProduct.getProductName())) {
                currentShopProduct.setBrand(shopProduct.getBrand());
                currentShopProduct.setCategory(shopProduct.getCategory());
                currentShopProduct.setMaxPrice(shopProduct.getMaxPrice());
                currentShopProduct.setProductPrice(shopProduct.getProductPrice());
                return true;
            }
        }
        return false;
    }

    public void addProduct(ShopProduct shopProduct) {
        shopProducts.add(shopProduct);
    }

    public boolean productExists(String productName) {
        for (ShopProduct shopProduct: shopProducts) {
            if (shopProduct.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
