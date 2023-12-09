import Bussines.ShopManager;
import Persistence.DAOJSON;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Presentation.Controller;
import Presentation.UI;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Initialization of essential components
        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();
        ShopManager shopManager = new ShopManager(shopDAO, productDAO);
        UI ui = new UI();

        // Creation and configuration of the controller
        Controller controller = new Controller(ui, shopManager);
        // Execution of the controller to initiate the application
        controller.run();
    }
}