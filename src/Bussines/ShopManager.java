package Bussines;

import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Bussines.Product.Product;
import Persistence.exception.PersistenceJsonException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Clase que gestiona las operaciones comerciales y de productos en la aplicación.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ShopManager {

    private final ShopDAO shopDAO;
    private final ProductDAO productDAO;

    /**
     * Constructor que inicializa un ShopManager con instancias de ShopDAO y ProductDAO.
     *
     * @param shopDAO    Instancia de ShopDAO para la gestión de tiendas.
     * @param productDAO Instancia de ProductDAO para la gestión de productos.
     */
    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
    }

    /**
     * Crea un nuevo producto y lo añade al catálogo.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param maxPrice Precio máximo del producto.
     * @param category Categoría del producto.
     */
    public void createProduct(String name, String brand, double maxPrice, ProductCategory category) throws PersistenceJsonException {
        Product product = new Product(name, brand, maxPrice, category);
        productDAO.addProduct(product);
    }

    /**
     * Elimina un producto del catálogo y de todas las tiendas.
     *
     * @param nameProduct Nombre del producto a eliminar.
     */
    public void removeProduct(String nameProduct) throws PersistenceJsonException {
        productDAO.removeProduct(nameProduct);
        shopDAO.removeProductFromShops(nameProduct);
    }

    /**
     * Crea una nueva tienda y la añade al sistema.
     *
     * @param shop Instancia de la tienda a crear.
     */
    public void createShop(Shop shop) throws PersistenceJsonException {
        shopDAO.addShop(shop);
    }

    /**
     * Añade un producto al catálogo de una tienda específica.
     *
     * @param shopName Nombre de la tienda.
     * @param sp       Producto de la tienda a añadir al catálogo.
     */
    public void expandCatalog(String shopName, ShopProduct sp) throws PersistenceJsonException {
        shopDAO.addProductInShop(shopName,sp);
    }

    /**
     * Elimina un producto del catálogo de una tienda específica.
     *
     * @param shopName    Nombre de la tienda.
     * @param productName Nombre del producto a eliminar del catálogo.
     */
    public void reduceCatalog(String shopName, String productName) throws PersistenceJsonException {
        shopDAO.removeProductFromShop(shopName,productName);
    }

    /**
     * Busca un producto por nombre.
     *
     * @param nameProduct Nombre del producto a buscar.
     * @return Instancia del producto encontrado.
     */
    public Product findProduct(String nameProduct) throws PersistenceJsonException {
        return  productDAO.findProduct(nameProduct);
    }

    /**
     * Busca productos que coincidan con una consulta dada.
     *
     * @param query Consulta para buscar productos.
     * @return Lista de productos que coinciden con la consulta.
     */
    public List<Product> searchProductsByQuery(String query) throws PersistenceJsonException {
        return productDAO.findProductsByQuery(query);
    }

    /**
     * Lee las revisiones de un producto específico.
     *
     * @param nameProduct Nombre del producto.
     * @return Lista de revisiones del producto.
     */
    public List<Review> readReviews(String nameProduct) throws PersistenceJsonException {
        return productDAO.findProduct(nameProduct).getReviews();
    }

    /**
     * Añade una revisión a un producto específico.
     *
     * @param nameProduct Nombre del producto.
     * @param review      Revisión a añadir.
     */
    public void makeReview(String nameProduct, Review review) throws PersistenceJsonException {
        Product product = productDAO.findProduct(nameProduct);
        product.addReview(review);
        productDAO.updateProduct(product);
    }

    /**
     * Verifica la existencia de los archivos necesarios para el sistema.
     *
     * @throws FileNotFoundException Excepción lanzada si los archivos no existen.
     */
    public void checkIfFileExists() throws FileNotFoundException {
        productDAO.checkIfFileExists();
    }

    /**
     * Obtiene la lista de todos los productos disponibles.
     *
     * @return Lista de todos los productos.
     */
    public List<Product> getAllProducts() throws PersistenceJsonException {
        return productDAO.getAllProducts();
    }

    /**
     * Obtiene la lista de todos los productos de una tienda específica.
     *
     * @param shopName Nombre de la tienda.
     * @return Lista de productos de la tienda.
     */
    public List<ShopProduct> getAllProductsFromShop(String shopName) throws PersistenceJsonException {
        return shopDAO.getProductsFromShop(shopName);
    }

    /**
     * Busca una tienda por nombre.
     *
     * @param shopName Nombre de la tienda a buscar.
     * @return Instancia de la tienda encontrada.
     */
    public Shop findShopByName(String shopName) throws PersistenceJsonException {
        List<Shop> list = shopDAO.getShops();
         return shopDAO.findShopByName(list,shopName);
    }
    /**
     * Obtiene la lista de tiendas donde un producto está disponible en el catálogo.
     *
     * @param productName Nombre del producto.
     * @return Lista de tiendas donde el producto está disponible.
     */
    public List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException {
        return shopDAO.getShopsWhereProductExistsInCatalog(productName);
    }
    /**
     * Obtiene un producto específico de una tienda.
     *
     * @param shopName    Nombre de la tienda.
     * @param productName Nombre del producto.
     * @return Instancia del producto en la tienda.
     */
    public ShopProduct getProductFromShop(String shopName,String productName) throws PersistenceJsonException {
        return shopDAO.getProductFromShop(shopName,productName);
    }
    /**
     * Obtiene la lista de nombres de todas las tiendas disponibles.
     *
     * @return Lista de nombres de tiendas.
     */
    public List<String> getAllNameShops() throws PersistenceJsonException {
        return shopDAO.getAllNameShops();
    }
    /**
     * Obtiene una tienda específica por nombre.
     *
     * @param shopName Nombre de la tienda.
     * @return Instancia de la tienda encontrada.
     */
    public Shop getShop(String shopName) throws PersistenceJsonException {
        return shopDAO.getShop(shopName);
    }
    /**
     * Obtiene la lista de nombres de todos los productos de una tienda.
     *
     * @param shopName Nombre de la tienda.
     * @return Lista de nombres de productos de la tienda.
     */
    public List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException {
        return shopDAO.getAllProductsNameFromShop(shopName);
    }
    /**
     * Realiza el proceso de pago y actualiza las ganancias de la tienda.
     *
     * @param s Tienda a procesar el pago.
     */
    public void checkout(Shop s) throws PersistenceJsonException {
        shopDAO.updateShop(s);
    }
}
