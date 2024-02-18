package Persistence;

import Bussines.Product.Product;
import Persistence.exception.PersistenceJsonException;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * La interfaz ProductDAO define las operaciones básicas que deben ser implementadas por cualquier
 * clase que actúe como un DAO (Data Access Object) para manipular productos en un sistema de persistencia.
 */
@SuppressWarnings("SpellCheckingInspection")
public interface ProductDAO {

    /**
     * Agrega un nuevo producto al sistema.
     *
     * @param product Producto que se va a agregar.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    void addProduct(Product product) throws PersistenceJsonException, ApiException;
    /**
     * Elimina un producto del sistema por su nombre.
     *
     * @param productName Nombre del producto que se va a eliminar.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    void removeProduct(String productName) throws PersistenceJsonException, ApiException;
    /**
     * Encuentra un producto en el sistema por su nombre.
     *
     * @param productName Nombre del producto que se va a buscar.
     * @return El objeto Product encontrado.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    Product findProduct(String productName) throws PersistenceJsonException, ApiException;
    /**
     * Obtiene todos los productos en el sistema.
     *
     * @return Una lista de todos los productos en el sistema.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    List<Product> getAllProducts() throws PersistenceJsonException, ApiException;
    /**
     * Actualiza la información de un producto en el sistema.
     *
     * @param product Producto con la información actualizada.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    void updateProduct(Product product) throws PersistenceJsonException, ApiException;
    /**
     * Busca productos en el sistema que coincidan con una consulta específica.
     *
     * @param query Consulta utilizada para buscar productos.
     * @return Una lista de productos que coinciden con la consulta.
     * @throws PersistenceJsonException Si hay un error al interactuar con el archivo JSON.
     * @throws ApiException              Si hay un error relacionado con la API.
     */
    List<Product> findProductsByQuery(String query) throws PersistenceJsonException, ApiException;
    /**
     * Verifica si el archivo que almacena la información de los productos existe.
     *
     * @throws FileNotFoundException Si el archivo no existe.
     */
    void checkIfFileExists() throws FileNotFoundException;
}
