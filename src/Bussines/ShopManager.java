package Bussines;

import Bussines.Product.*;
import Bussines.Shop.Shop;
import Bussines.exception.PersistenceIntegrationException;
import Persistence.*;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.List;
/**
 * Clase que gestiona las operaciones relacionadas con tiendas y productos.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ShopManager {
    private  ShopDAO shopDAO;
    private  ProductDAO productDAO;
    /**
     * Constructor de la clase ShopManager.
     * @param options Opciones para seleccionar la fuente de datos (API o JSON).
     */
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
    /**
     * Crea un nuevo producto y lo agrega a la fuente de datos.
     * @param name Nombre del producto.
     * @param brand Marca del producto.
     * @param maxPrice Precio máximo del producto.
     * @param category Categoría del producto.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
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
    /**
     * Elimina un producto de la fuente de datos y de las tiendas donde esté presente.
     * @param nameProduct Nombre del producto a eliminar.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public void removeProduct(String nameProduct) throws PersistenceIntegrationException {
        try {
            this.productDAO.removeProduct(nameProduct);
            this.shopDAO.removeProductFromShops(nameProduct);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Crea una nueva tienda y la agrega a la fuente de datos.
     * @param shop Tienda a agregar.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public void createShop(Shop shop) throws PersistenceIntegrationException {
        try {
            this.shopDAO.addShop(shop);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Expande el catálogo de productos de una tienda.
     * @param shopName Nombre de la tienda.
     * @param sp Producto a agregar en la tienda.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public void expandCatalog(String shopName, ShopProduct sp) throws PersistenceIntegrationException {
        try {
            this.shopDAO.addProductInShop(shopName, sp);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Reduce el catálogo de productos de una tienda.
     * @param shopName Nombre de la tienda.
     * @param productName Nombre del producto a eliminar de la tienda.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public void reduceCatalog(String shopName, String productName) throws PersistenceIntegrationException {
        try {
            this.shopDAO.removeProductFromShop(shopName, productName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Busca un producto por su nombre.
     * @param nameProduct Nombre del producto.
     * @return El producto encontrado.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public Product findProduct(String nameProduct) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProduct(nameProduct);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Busca productos que coincidan con una consulta.
     * @param query Consulta para buscar productos.
     * @return Lista de productos encontrados.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<Product> searchProductsByQuery(String query) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProductsByQuery(query);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Lee las reseñas de un producto.
     * @param nameProduct Nombre del producto.
     * @return Lista de reseñas del producto.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<Review> readReviews(String nameProduct) throws PersistenceIntegrationException {
        try {
            return this.productDAO.findProduct(nameProduct).getReviews();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Añade una reseña a un producto.
     * @param nameProduct Nombre del producto.
     * @param review Reseña a añadir.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
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
    /**
     * Comprueba si el archivo de productos existe.
     * @throws RuntimeException Si el archivo no existe.
     */
    public void checkIfFileExists() throws RuntimeException {
        try {
            this.productDAO.checkIfFileExists();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Obtiene todos los productos de la fuente de datos.
     * @return Lista de productos.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<Product> getAllProducts() throws PersistenceIntegrationException {
        try {
            return this.productDAO.getAllProducts();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene todos los productos de una tienda.
     * @param shopName Nombre de la tienda.
     * @return Lista de productos de la tienda.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<ShopProduct> getAllProductsFromShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getProductsFromShop(shopName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene todas las tiendas de la fuente de datos.
     * @return Lista de tiendas.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<Shop> getAllShops() throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShops();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Busca una tienda por su nombre.
     * @param shopName Nombre de la tienda.
     * @return La tienda encontrada.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public Shop findShopByName(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShop(shopName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene todas las tiendas que tienen un producto en su catálogo.
     * @param productName Nombre del producto.
     * @return Lista de tiendas que tienen el producto.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShopsWhereProductExistsInCatalog(productName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene un producto de una tienda.
     * @param shopName Nombre de la tienda.
     * @param productName Nombre del producto.
     * @return Producto de la tienda.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getProductFromShop(shopName, productName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene todos los nombres de las tiendas.
     * @return Lista de nombres de tiendas.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<String> getAllNameShops() throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getAllNameShops();
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene una tienda por su nombre.
     * @param shopName Nombre de la tienda.
     * @return La tienda encontrada.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public Shop getShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getShop(shopName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Obtiene todos los nombres de productos de una tienda.
     * @param shopName Nombre de la tienda.
     * @return Lista de nombres de productos de la tienda.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceIntegrationException {
        try {
            return this.shopDAO.getAllProductsNameFromShop(shopName);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
    /**
     * Realiza el proceso de compra en una tienda y actualiza la información.
     * @param s Tienda donde se realiza la compra.
     * @throws PersistenceIntegrationException Si hay un error al integrar con la capa de persistencia.
     */
    public void checkout(Shop s) throws PersistenceIntegrationException {
        try {
            this.shopDAO.updateShop(s);
        } catch (PersistenceJsonException | ApiException e) {
            throw new PersistenceIntegrationException("",e);
        }
    }
}