package Persistence;

import Bussines.Product.ShopProduct;
import Bussines.Shop.Shop;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.util.List;
/**
 * Interfaz que define operaciones de acceso a datos para las tiendas (Shop).
 */
@SuppressWarnings("SpellCheckingInspection")
public interface ShopDAO {
    /**
     * Obtiene una tienda por su nombre.
     *
     * @param shopName Nombre de la tienda.
     * @return Objeto Shop correspondiente al nombre proporcionado.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    Shop getShop(String shopName) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene una lista de todas las tiendas.
     *
     * @return Lista de objetos Shop que representan todas las tiendas.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    List<Shop> getShops() throws PersistenceJsonException, ApiException;
    /**
     * Obtiene una lista de tiendas donde existe un producto en el catálogo.
     *
     * @param productName Nombre del producto.
     * @return Lista de tiendas que tienen el producto en su catálogo.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    List<Shop> getShopsWhereProductExistsInCatalog(String productName) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene una lista de nombres de todas las tiendas.
     *
     * @return Lista de nombres de tiendas.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    List<String> getAllNameShops() throws PersistenceJsonException, ApiException;
    /**
     * Agrega una tienda.
     *
     * @param shop Objeto Shop que se va a agregar.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void addShop(Shop shop) throws PersistenceJsonException, ApiException;
    /**
     * Actualiza la información de una tienda.
     *
     * @param shop Objeto Shop con la información actualizada.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void updateShop(Shop shop) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene una lista de productos de una tienda.
     *
     * @param shopName Nombre de la tienda.
     * @return Lista de objetos ShopProduct que representan los productos de la tienda.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    List<ShopProduct> getProductsFromShop(String shopName) throws PersistenceJsonException, ApiException;
    /**
     * Agrega un producto a una tienda.
     *
     * @param shopName    Nombre de la tienda.
     * @param shopProduct Objeto ShopProduct que se va a agregar.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void addProductInShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException, ApiException;
    /**
     * Actualiza la información de un producto en una tienda.
     *
     * @param shopName    Nombre de la tienda.
     * @param shopProduct Objeto ShopProduct con la información actualizada.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void updateProductFromShop(String shopName, ShopProduct shopProduct) throws PersistenceJsonException, ApiException;
    /**
     * Elimina un producto de una tienda.
     *
     * @param shopName       Nombre de la tienda.
     * @param shopProductName Nombre del producto que se va a eliminar.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void removeProductFromShop(String shopName, String shopProductName) throws PersistenceJsonException, ApiException;
    /**
     * Elimina un producto de todas las tiendas.
     *
     * @param nameProduct Nombre del producto que se va a eliminar.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    void removeProductFromShops(String nameProduct) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene una lista de nombres de todos los productos de una tienda.
     *
     * @param shopName Nombre de la tienda.
     * @return Lista de nombres de productos de la tienda.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    List<String> getAllProductsNameFromShop(String shopName) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene un producto específico de una tienda.
     *
     * @param shopName    Nombre de la tienda.
     * @param productName Nombre del producto.
     * @return Objeto ShopProduct que representa el producto de la tienda.
     * @throws PersistenceJsonException Si hay un problema con la persistencia JSON.
     * @throws ApiException             Si hay un problema con la API.
     */
    ShopProduct getProductFromShop(String shopName, String productName) throws PersistenceJsonException, ApiException;
}
