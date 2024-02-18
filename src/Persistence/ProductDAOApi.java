package Persistence;

import Bussines.Product.Product;
import Persistence.exception.PersistenceJsonException;

import java.io.FileNotFoundException;
import java.util.List;

public class ProductDAOApi implements ProductDAO {
    @Override
    public void addProduct(Product product) throws PersistenceJsonException {

    }

    @Override
    public void removeProduct(String productName) throws PersistenceJsonException {

    }

    @Override
    public Product findProduct(String productName) throws PersistenceJsonException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceJsonException {
        return null;
    }

    @Override
    public void updateProduct(Product product) throws PersistenceJsonException {

    }

    @Override
    public List<Product> findProductsByQuery(String query) throws PersistenceJsonException {
        return null;
    }

    @Override
    public void checkIfFileExists() throws FileNotFoundException {
        //Da error si no se implementa este metodo.
    }
}
