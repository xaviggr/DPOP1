import Bussines.DataSourceOptions;
import Bussines.ShopCart;
import Bussines.ShopManager;
import Persistence.ProductDAOJSON;
import Persistence.ShopDAOJSON;
import Presentation.Controller;
import Presentation.UI;
import edu.salle.url.api.ApiHelper;
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

        //ProductDAOJSON productDAO = new ProductDAOJSON();
        //ShopDAOJSON shopDAO = new ShopDAOJSON();

        UI ui = new UI();
        ShopCart shopCart = new ShopCart();


        // Creation and configuration of the controller
        Controller controller = new Controller(ui, shopCart);
        // Execution of the controller to initiate the application
        controller.run();
    }
}