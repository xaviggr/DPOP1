import Bussines.ShopCart;
import Bussines.ShopManager;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Presentation.Controller;
import Presentation.UI;
import edu.salle.url.api.exception.ApiException;

/**
 * Clase principal que inicia la aplicación.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    /**
     * Método principal que inicializa y ejecuta la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) throws ApiException {
        // Initialization of essential components
        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();
        ShopManager shopManager = new ShopManager(shopDAO, productDAO);
        UI ui = new UI();
        ShopCart shopCart = new ShopCart();

        // Initialization of the API helper
        //ApiHelper apiHelper = new ApiHelper();

        // Creation and configuration of the controller
        Controller controller = new Controller(ui, shopManager, shopCart);
        // Execution of the controller to initiate the application
        controller.run();
    }
}