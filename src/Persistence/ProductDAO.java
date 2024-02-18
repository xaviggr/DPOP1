package Persistence;

import Bussines.Product.Product;
import Persistence.exception.PersistenceJsonException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductDAO {

    void addProduct(Product product) throws PersistenceJsonException;
    void removeProduct(String productName) throws PersistenceJsonException;
    Product findProduct(String productName) throws PersistenceJsonException;
    List<Product> getAllProducts() throws PersistenceJsonException;
    void updateProduct(Product product) throws PersistenceJsonException;
    List<Product> findProductsByQuery(String query) throws PersistenceJsonException;
    void checkIfFileExists() throws FileNotFoundException;
}
