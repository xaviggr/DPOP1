package Presentation;

import Bussines.Product.ProductCategory;
import Bussines.Shop;
import Bussines.ShopManager;

import java.io.FileNotFoundException;



public class Controller {

    private UI ui;
    private ShopManager shopManager;

    public Controller(UI ui, ShopManager shopManager) {
        this.ui = ui;
        this.shopManager = shopManager;
    }

    public void run() {
        ui.showMainLogo();
        try {
            checkToRun();
            startMenu();
        } catch (FileNotFoundException e) {
            ui.showErrorLoadingFiles();
        }
    }

    private void checkToRun() throws FileNotFoundException {
        shopManager.checkIfFileExists();
    }

    // Nueva funcion
    // Method to start the main menu
    private void startMenu() {
        int option;
        do {
            ui.showMenu(); // Display the main menu

            option = ui.askForInteger("Choose a Digital Shopping Experience: "); // Ask the user for the chosen option
            executeOption(option); // Execute the selected option
        } while(option != 4); // Continue until the exit option is selected
    }

    // Method to execute the selected option from the main menu
    private void executeOption(int option) {
        ui.showMessage(""); // Display a blank message
        int choose;
        switch (option) {
            case 1:
                //Manage products.
                 choose = ui.showProductsMenu();
                 productsLoop(choose);
                break;
            case 2:
                // Manage shops
                choose = ui.showShopsMenu();
                shopsLoop(choose);
                break;
            case 3:
                //Search products
                String productName = ui.askForString("Enter your query: ");
                shopManager.findProduct(productName);

                break;
            case 4:
                // List shops

                break;
            case 5:
                // Your cart.

                break;
            default:
                ui.showMessage("Incorrect option"); // Message for incorrect option
                break;
        }
    }

    // New function
    private void productsLoop(int choose) {
        switch (choose) {
            case 1:
                createProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:

                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }

    // New function
    private void shopsLoop(int choose) {

        switch (choose) {
            case 1:
                //Create shop
                createShop();
                break;
            case 2:
                // Expand shop catalogue
                expandCatalog();
                break;
            case 3:
                // Reduce Shop catalogue
                reduceCatalog();
                break;
            case 4:
                //Back
                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }

    private void createProduct() {
        String name = ui.askForString("Enter the name of the product");
        String description = ui.askForString("Enter the description of the product");
        double maxPrice = ui.askForDouble("Enter the price of the product");
        ProductCategory category = ProductCategory.valueOf(ui.askForString("Enter the category of the product"));
        shopManager.createProduct(name, description, maxPrice, category);
    }

    private void removeProduct() {
        String productName = ui.askForString("Enter the name of the product");
        shopManager.removeProduct(productName);
    }

    // New function
    private String giveShopModel() {
        String userInput;
        boolean isValid = false;

        do {
            userInput = ui.askForString("Please pick the shop’s business model: ").toUpperCase();

            if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C")) {
                isValid = true;
            } else {
                System.out.println("Please enter a valid option (A, B, or C).");
            }
        } while (!isValid);

        return switch (userInput) {
            case "A" -> "Maximum Benefits";
            case "B" -> "Loyalty";
            case "C" -> "Sponsored";
            default -> "Invalid Option";
        };

    }

    private void createShop() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String description = ui.askForString("Please enter the shop's description: ");
        int foundingYear = ui.askForInteger("Please enter the shop's founding year: ");
        String businessModel = giveShopModel();

        Shop s = new Shop(shopName,description,foundingYear, 0,businessModel,null);
        shopManager.createShop(s);
    }

    private void expandCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String productName = ui.askForString("Please enter the product's name: ");
        double price = ui.askForDouble("Please enter the product's price at this shop");
        shopManager.expandCatalog(shopName, productName, price);
    }

    private void reduceCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        shopManager.reduceCatalog(shopName);
    }

    private void addToCard() {

    }

    private void clearCard() {

    }

    private void checkout() {

    }

    private void readReviews() {
        // List reviews from product selected.

    }

    private void makeReviews() {

    }

    // Method to exit the program
    private void exit() {
        ui.showMessage("We hope to see you again!");
    }
}
