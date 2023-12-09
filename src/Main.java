import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Shop;
import Bussines.ShopManager;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Presentation.Controller;
import Presentation.UI;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialization of essential components
        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();
        ShopManager shopManager = new ShopManager(shopDAO, productDAO);
        UI ui = new UI();

        //shopDAO.addProductInShop("elCofre Copy Shop", new ShopProduct("Coca Cola", "CSGO", 80.0, ProductCategory.GENERAL, 7.0));
        //shopDAO.updateProductFromShop("elCofre Copy Shop", new ShopProduct("Coca Cola", "TEST", 40.0, ProductCategory.GENERAL, 7.0));
        shopDAO.removeProductFromShop("elCofre Copy Shop", "Coca Cola");

        // Creation and configuration of the controller
        Controller controller = new Controller(ui, shopManager);
        // Execution of the controller to initiate the application
        controller.run();
    }
}