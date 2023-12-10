package Presentation;

import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Review;
import Bussines.Shop;
import Bussines.ShopManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("unused")
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
                 productInteraction(choose);
                break;
            case 2:
                // Manage shops
                choose = ui.showShopsMenu();
                shopInteraction(choose);
                break;
            case 3:
                //Search products
                searchProducts();
                break;
            case 4:
                // List shops
                listShops();
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

    //MENUS INTERACTION
    private void shopInteraction(int choose) {

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
    private void searchProductInteraction(int choose, Product p) {

        switch (choose) {
            case 1:
                ui.showMessage("");
                readReviews(p);
                break;
            case 2:
                ui.showMessage("");
                makeReviews(p);
                break;
            case 3:
                //Back
                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }
    private void productInteraction(int choose) {
        switch (choose) {
            case 1:
                ui.showMessage("");
                createProduct();
                break;
            case 2:
                ui.showMessage("");
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
    private void listShopsInteraction(int choose, Product p) {

        switch (choose) {
            case 1:
                ui.showMessage("");
                readReviews(p);
                break;
            case 2:
                ui.showMessage("");
                makeReviews(p);
                break;
            case 3:
                ui.showMessage("");
                //addToCart();
                break;
            case 4:
                //Back
                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }

    //SHOPS
    private void createShop() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String description = ui.askForString("Please enter the shop's description: ");
        int foundingYear = ui.askForInteger("Please enter the shop's founding year: ");
        String businessModel = ui.askForShopModel();

        Shop s = new Shop(shopName,description,foundingYear, 0,businessModel,null);
        shopManager.createShop(s);
        ui.showMessage("'" + shopName + "'"  + " is now a part of the elCofre family.\n");
    }
    private void expandCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String productName = ui.askForString("Please enter the product's name: ");
        double price = ui.askForDouble("Please enter the product's price at this shop: ");
        Product p = shopManager.findProduct(productName);
        Shop s = shopManager.findShopByName(shopName);
        if (p == null || s == null) {
            if(p == null) {
                ui.showMessage("Error. That product doesn't exist.\n");
            } else {
                ui.showMessage("Error. That shop doesn't exist.\n");
            }
        } else {
            ShopProduct sp = new ShopProduct(p.getProductName(),p.getProductBrand(),p.getMaxPrice(),p.getCategory(),price);
            shopManager.expandCatalog(shopName, sp);
            ui.showMessage("'" + p.getProductName() +"'" + " by " + "'"+p.getProductBrand()+"'" + " is now being sold at " + "'"+shopName+"'"+".\n");
        }

    }
    private void reduceCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        ui.showMessage("This shop sells the following products:");
        List<String> shopProductsNames = shopManager.getAllProductsNameFromShop(shopName);
        if (shopProductsNames.isEmpty()) {
            ui.showMessage("This shop doesn't exists.");
            return;
        }
        int index = ui.showListAndGetChoice(shopProductsNames, "Which one would you like to remove? ");
        if (ui.isValidIndex(index, shopProductsNames.size() - 1)) {
            String productName = shopProductsNames.get(index - 1);
            shopManager.reduceCatalog(shopName, productName);
            ui.showMessage("\"" + productName + "\"" + " is no longer being sold at " + "\"" + shopName + "\".\n");

        }
    }
    private void listShops() {
        List<String> shopNames = shopManager.getAllNameShops();

        int choose = ui.showListAndGetChoice(shopNames, "Which catalogue do you want to see? ");
        if (ui.isValidIndex(choose, shopNames.size() - 1) && !shopNames.isEmpty())  {
            Shop s = shopManager.getShop(shopNames.get(choose - 1));
            List<ShopProduct> products = shopManager.getAllProductsFromShop(s.getName());
            ui.showShopCatalogue(s, products);
            int element = ui.askForInteger("Which one are you interested in? ");

            if (ui.isValidIndex(element, products.size() - 1)){
                ShopProduct sp = products.get(element - 1);
                int selected = ui.showCatalogMenu("Choose an option: ");
                listShopsInteraction(selected, sp);
            }
        }
    }

    //PRODUCTS
    private void createProduct() {
        String name = ui.askForString("Please enter the product’s name: ");
        String brand = ui.askForString("Please enter the product’s brand: ");
        double maxPrice = ui.askForDouble("Please enter the product’s maximum retail price: ");
        ProductCategory category = ui.askForProductCategory();
        shopManager.createProduct(name, brand, maxPrice, category);
        ui.showMessage("The product " + "\"" + name + "\"" + " by \"" + brand + "\" was added to the system.\n");
    }
    private void removeProduct() {
        List<Product> products = shopManager.getAllProducts();
        List<String> productData = new ArrayList<>();
        products.forEach(p -> productData.add("\"" +p.getProductName() + "\" by \"" + p.getProductBrand() + "\""));

        if (products.isEmpty()) {
            ui.showMessage("There are no products available.");
            return;
        } else {
            ui.showMessage("These are the currently available products: ");
        }

        int index = ui.showListAndGetChoice(productData, "Which product would you like to remove? ");

        if (ui.isValidIndex(index, productData.size() - 1)) {
            Product p = products.get(index - 1);
            if (ui.askForConfirmation(p.getProductName(), p.getProductBrand())) {
                shopManager.removeProduct(p.getProductName());
                ui.showMessage("\"" + p.getProductName() + "\"" + " by " + "\"" + p.getProductBrand()+ "\"" + " has been withdrawn from sale.\n");
            } else {
                removeProduct();
            }
        }
    }
    private void searchProducts() {
        String productName = ui.askForString("Enter your query: ");
        LinkedHashMap<Product, LinkedHashMap<Shop, Double>> products = getDictionaryWithProductAndShopsWhereExistsWithPrice(productName);
        if (products != null) {
            ui.showProductSearched(products);
            int choose = ui.askForInteger("Which one would you like to review?");

            if (ui.isValidIndex(choose, products.size() - 1)) {
                int selected = ui.showReviewMenu("Choose an option: ");
                Product p = (Product) products.keySet().toArray()[choose - 1];
                searchProductInteraction(selected, p);
            }
        } else {
            ui.showMessage("No products found.");
        }
    }
    private LinkedHashMap<Product, LinkedHashMap<Shop, Double>> getDictionaryWithProductAndShopsWhereExistsWithPrice(String productName) {
        LinkedHashMap<Product, LinkedHashMap<Shop, Double>> products = new LinkedHashMap<>();
        List<Product> productsFound = shopManager.searchProductsByQuery(productName);
        if (productsFound.isEmpty()) {
            return null;
        } else {
            for (Product p : productsFound) {
                List<Shop> shops = shopManager.getShopsWhereProductExistsInCatalog(p.getProductName());
                LinkedHashMap<Shop, Double> shopsWithPrice = new LinkedHashMap<>();
                for (Shop s : shops) {
                    ShopProduct sp = shopManager.getProductFromShop(s.getName(), p.getProductName());
                    shopsWithPrice.put(s, sp.getProductPrice());
                }
                products.put(p, shopsWithPrice);
            }
            return products;
        }
    }

    //CART
    private void addToCart() {

    }
    private void clearCart() {

    }
    private void checkout() {

    }

    //REVIEWS
    private void readReviews(Product p) {
        // List reviews from product selected.
        List<Review> reviews = shopManager.readReviews(p.getProductName());
        if (reviews.isEmpty()) {
            ui.showMessage("There are no reviews for this product yet.");
        } else {
            ui.showMessage("These are the reviews for \"" + p.getProductName() + "\" by \"" + p.getProductBrand() + "\":");
            float sum = 0;
            for (Review r : reviews) {
                sum += r.getStars();
                ui.showMessage("\t" + r.getStars() + "* " + r.getCommentary());
            }
            ui.showMessage("Average rating: " + (sum/reviews.size()) + "\n");
        }
    }
    private void makeReviews(Product p) {
        // Make a review for the product selected.
        String stars = ui.askForString("Please rate the product (1-5 stars): ");
        String commentary = ui.askForString("Please add a comment to your review: ");
        Review review = new Review(stars, commentary);
        shopManager.makeReview(p.getProductName(), review);

        ui.showMessage("Thank you for your review of \"" + p.getProductName() + "\" by \"" + p.getProductBrand() + "\"");
    }

    //EXIT
    private void exit() {
        ui.showMessage("We hope to see you again!");
    }
}