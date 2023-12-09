package Persistence;
import Bussines.Product.Product;

public class ProductDAO extends DAOJSON {

    public ProductDAO() {
        super();
        this.path = "products.json";
    }

    public boolean addProduct(Product product) {
        return false;
    }

    public boolean removeProduct(Product product) {
        return false;
    }

    public Product findProduct(String productName) {
        return null;
    }
}
