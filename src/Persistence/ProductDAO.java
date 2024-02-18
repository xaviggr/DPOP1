package Persistence;

import Bussines.Product.Product;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductDAO {

    void addProduct(Product product) throws PersistenceJsonException, ApiException;
    void removeProduct(String productName) throws PersistenceJsonException, ApiException;
    Product findProduct(String productName) throws PersistenceJsonException, ApiException;
    List<Product> getAllProducts() throws PersistenceJsonException, ApiException;
    void updateProduct(Product product) throws PersistenceJsonException, ApiException;
    List<Product> findProductsByQuery(String query) throws PersistenceJsonException, ApiException;

    void checkIfFileExists() throws FileNotFoundException;
}
