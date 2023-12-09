package Presentation;

import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Shop;
import Bussines.ShopManager;

import java.io.FileNotFoundException;

public class Controller {

    private final UI ui;
    private final ShopManager shopManager;

    public Controller(UI ui, ShopManager shopManager) {
        this.ui = ui;
        this.shopManager = shopManager;
    }

    public void run() {
        ui.showMainLogo();
        try {
            checkToRun();
            ui.showFileConfirmation();
            startMenu();
        } catch (FileNotFoundException e) {
            ui.showErrorLoadingFiles();
        }
    }

    private void checkToRun() throws FileNotFoundException {
        shopManager.checkIfFileExists();
    }

    // New function
    private void startMenu() {
        int option;

        do {
            ui.showMenu(); // Display the main menu

            option = ui.askForInteger("Choose a Digital Shopping Experience: "); // Ask the user for the chosen option
            executeOption(option); // Execute the selected option
        } while(option != 6); // Continue until the exit option is selected
    }

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

                break;
            case 4:
                // List shops

                break;
            case 5:
                // Your cart.

                break;
            case 6:
                exit();
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
                //Back
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
                ui.showMessage("");
                createShop();
                break;
            case 2:
                // Expand shop catalogue
                ui.showMessage("");
                expandCatalog();
                break;
            case 3:
                // Reduce Shop catalogue
                ui.showMessage("");
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
        String name = ui.askForString("Please enter the product’s name: ");
        String brand = ui.askForString("Please enter the product’s brand: ");
        double maxPrice = ui.askForDouble("Please enter the product’s maximum retail price: ");
        ProductCategory category = giveProductCategory();
        shopManager.createProduct(name, brand, maxPrice, category);
        ui.showMessage("The product " + "'"+name+"'" + " by " + "'"+brand+"'" + " was added to the system.\n");
    }

    //new Function
    private ProductCategory giveProductCategory() {
        String userInput;

        boolean isValid = false;
        ui.showMessage("");
        ui.giveProductCategory();
        do {
            userInput = ui.askForString("Please pick the shop’s business model: ").toUpperCase();

            if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C")) {
                isValid = true;
            } else {
                ui.showMessage("Please enter a valid option (A, B, or C).");
            }
        } while (!isValid);

        return switch (userInput) {
            case "A" -> ProductCategory.GENERAL;
            case "B" -> ProductCategory.REDUCED_TAXES;
            case "C" -> ProductCategory.SUPER_REDUCED_TAXES;
            default -> ProductCategory.valueOf("Invalid option");
        };
    }

    //NEEDS WORK WHEN DAO IS DONE
    private void removeProduct() {
        String productName = ui.askForString("Enter the name of the product: ");

        // list all products here
        //ask user to choose product get name of that product and pass it to the functions.


        Product p = shopManager.findProduct(productName);
        int option = ui.askForConfirmation(p.getProductName(), p.getProductBrand());

        if(option == 1) {
            shopManager.removeProduct(productName);
            ui.showMessage("'" + p.getProductName() +"'" + " by " + "'"+p.getProductBrand()+"'" + " has been withdrawn from sale.\n");
        }
        else {
            ui.showMessage("Operation Canceled\n");
        }


    }

    // New function
    private String giveShopModel() {
        String userInput;
        boolean isValid = false;
        ui.showMessage("");
        ui.giveBusinessModel();
        do {
            userInput = ui.askForString("Please pick the shop’s business model: ").toUpperCase();

            if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C")) {
                isValid = true;
            } else {
                ui.showMessage("Please enter a valid option (A, B, or C).");
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

        Shop s = new Shop(shopName,description,foundingYear,businessModel);
        shopManager.createShop(s);
        ui.showMessage("'" + shopName + "'"  + " is now a part of the elCofre family.\n");
    }

    private void expandCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String productName = ui.askForString("Please enter the product's name: ");
        double price = ui.askForDouble("Please enter the product's price at this shop");
        Product p = shopManager.findProduct(productName);
        if (p == null) {
            ui.showMessage("Error. That product doesn't exist.\n");
        } else {
            shopManager.expandCatalog(shopName, productName, price);
            ui.showMessage("'" + p.getProductName() +"'" + " by " + "'"+p.getProductBrand()+"'" + " is now being sold at " + "'"+shopName+"'"+".\n");
        }

    }

    //NEEDS WORK WHEN DAO IS DONE
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

    private void exit() {
        ui.showMessage("We hope to see you again!");
    }
}
