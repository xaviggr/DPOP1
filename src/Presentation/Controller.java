package Presentation;

import Bussines.Product.Product;
import Bussines.Product.ShopProduct;
import Bussines.Review;
import Bussines.Shop.Shop;
import Bussines.ShopCart;
import Bussines.ShopManager;
import Persistence.exception.PersistenceJsonException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Controller {
    private final UI ui;
    private final ShopManager shopManager;
    private final ShopCart shopCart;

    public Controller(UI ui, ShopManager shopManager, ShopCart shopCart) {
        this.ui = ui;
        this.shopManager = shopManager;
        this.shopCart = shopCart;
    }

    public void run() {
        this.ui.showMainLogo();

        try {
            this.checkToRun();
            this.ui.showFileConfirmation();
            this.startMenu();
        } catch (FileNotFoundException var2) {
            this.ui.showErrorLoadingFiles();
        }

    }

    private void checkToRun() throws FileNotFoundException {
        this.shopManager.checkIfFileExists();
    }

    private void startMenu() {
        int option;
        do {
            this.ui.showMenu();
            option = this.ui.askForInteger("Choose a Digital Shopping Experience: ");
            this.executeOption(option);
        } while(option != 6);

    }

    private void executeOption(int option) {
        this.ui.showMessage("");
        int choose;
        switch (option) {
            case 1:
                choose = this.ui.showProductsMenu();
                this.productInteraction(choose);
                break;
            case 2:
                choose = this.ui.showShopsMenu();
                this.shopInteraction(choose);
                break;
            case 3:
                this.searchProducts();
                break;
            case 4:
                this.listShops();
                break;
            case 5:
                this.ui.showCart(this.shopCart);
                choose = this.ui.showCartMenu();
                this.shopCartInteraction(choose);
                break;
            case 6:
                this.exit();
                break;
            default:
                this.ui.showMessage("Incorrect option");
        }

    }

    private void shopInteraction(int choose) {
        switch (choose) {
            case 1:
                this.ui.showMessage("");
                this.createShop();
                break;
            case 2:
                this.ui.showMessage("");
                this.expandCatalog();
                break;
            case 3:
                this.ui.showMessage("");
                this.reduceCatalog();
            case 4:
                break;
            default:
                this.ui.showMessage("Invalid option.");
        }

    }

    private void searchProductInteraction(int choose, Product p) {
        switch (choose) {
            case 1:
                this.ui.showMessage("");
                this.readReviews(p);
                break;
            case 2:
                this.ui.showMessage("");
                this.makeReviews(p);
            case 3:
                break;
            default:
                this.ui.showMessage("Invalid option.");
        }

    }

    private void productInteraction(int choose) {
        switch (choose) {
            case 1:
                this.ui.showMessage("");
                this.createProduct();
                break;
            case 2:
                this.ui.showMessage("");
                this.removeProduct();
            case 3:
                break;
            default:
                this.ui.showMessage("Invalid option.");
        }

    }

    private void listShopsInteraction(int choose, ShopProduct p) {
        switch (choose) {
            case 1:
                this.ui.showMessage("");
                this.readReviews(p.getProduct());
                break;
            case 2:
                this.ui.showMessage("");
                this.makeReviews(p.getProduct());
                break;
            case 3:
                this.ui.showMessage("");
                this.addToCart(p);
            case 4:
                break;
            default:
                this.ui.showMessage("Invalid option.");
        }

    }

    private void shopCartInteraction(int choose) {
        switch (choose) {
            case 1:
                this.ui.showMessage("");
                this.checkout();
                break;
            case 2:
                this.ui.showMessage("");
                this.clearCart();
            case 3:
                break;
            default:
                this.ui.showMessage("Invalid option.");
        }

    }

    private void createShop() {
        String shopName = this.ui.askForString("Please enter the shop's name: ");
        String description = this.ui.askForString("Please enter the shop's description: ");
        int foundingYear = this.ui.askForPositiveInteger("Please enter the shop's founding year: ");
        String businessModel = this.ui.askForShopModel();
        Shop s = new Shop(shopName, description, foundingYear, 0.0, businessModel, new ArrayList<>());

        try {
            this.shopManager.createShop(s);
        } catch (PersistenceJsonException var7) {
            throw new RuntimeException(var7);
        }

        this.ui.showMessage("'" + shopName + "' is now a part of the elCofre family.\n");
    }

    private void expandCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String productName = ui.askForString("Please enter the product's name: ");
        double price = ui.askForPositiveInteger("Please enter the product's price at this shop: ");

        Product p;
        Shop s;

        try {
            p = shopManager.findProduct(productName);
            s = shopManager.findShopByName(shopName);

            if (p == null || s == null) {
                if(p == null) {
                    ui.showMessage("Error. That product doesn't exist.\n");
                } else {
                    ui.showMessage("Error. That shop doesn't exist.\n");
                }
            } else {
                if (p.getMaxPrice() < price) {
                    ui.showMessage("Error. The price is higher than the maximum retail price.\n");
                    return;
                }
                ShopProduct sp = new ShopProduct(p,price);
                shopManager.expandCatalog(shopName, sp);
                ui.showMessage("'" + p.getProductName() +"'" + " by " + "'"+p.getBrand()+"'" + " is now being sold at " + "'"+shopName+"'"+".\n");
            }

        } catch (PersistenceJsonException e) {
            //Mensaje de error
            throw new RuntimeException(e);
        }
    }

    private void reduceCatalog() {
        String shopName = this.ui.askForString("Please enter the shop's name: ");

        try {
            Shop s = this.shopManager.findShopByName(shopName);
            if (s == null) {
                this.ui.showMessage("This shop doesn't exists.");
            } else {
                List<String> shopProductsNames = this.shopManager.getAllProductsNameFromShop(shopName);
                this.ui.showMessage("This shop sells the following products:");
                int index = this.ui.showListAndGetChoice(shopProductsNames, "Which one would you like to remove? ");
                if (this.ui.isValidIndex(index, shopProductsNames.size() - 1)) {
                    String productName = shopProductsNames.get(index - 1);
                    this.shopManager.reduceCatalog(shopName, productName);
                    this.ui.showMessage("\"" + productName + "\" is no longer being sold at \"" + shopName + "\".\n");
                }

            }
        } catch (PersistenceJsonException var6) {
            throw new RuntimeException(var6);
        }
    }

    private void listShops() {
        try {
            List<String> shopNames = this.shopManager.getAllNameShops();
            int choose = this.ui.showListAndGetChoice(shopNames, "Which catalogue do you want to see? ");
            if (this.ui.isValidIndex(choose, shopNames.size() - 1) && !shopNames.isEmpty()) {
                Shop s = this.shopManager.getShop(shopNames.get(choose - 1));
                List<ShopProduct> products = this.shopManager.getAllProductsFromShop(s.getName());
                if (!products.isEmpty()) {
                    this.ui.showShopCatalogue(s, products);
                    int element = this.ui.askForInteger("Which one are you interested in? ");
                    if (this.ui.isValidIndex(element, products.size() - 1)) {
                        ShopProduct sp = products.get(element - 1);
                        int selected = this.ui.showCatalogMenu("Choose an option: ");
                        this.listShopsInteraction(selected, sp);
                    }
                } else {
                    this.ui.showMessage("This shop doesn't have any products in its catalogue\n");
                }

            } else {
                this.ui.showMessage("Error, invalid Option.\n");
            }

        } catch (PersistenceJsonException var8) {
            throw new RuntimeException(var8);
        }
    }

    private void createProduct() {
        String name = this.ui.askForString("Please enter the product’s name: ");
        String brand = this.ui.askForString("Please enter the product’s brand: ");
        double maxPrice = this.ui.askForPositiveDouble("Please enter the product’s maximum retail price: ");
        String category = this.ui.askForProductCategory();

        try {
            if (this.shopManager.findProduct(name) != null) {
                this.ui.showMessage("Error. That product already exists.\n");
            } else {
                this.shopManager.createProduct(name, brand, maxPrice, category);
                this.ui.showMessage("The product \"" + name + "\" by \"" + brand + "\" was added to the system.\n");
            }
        } catch (PersistenceJsonException var7) {
            throw new RuntimeException(var7);
        }
    }

    private void removeProduct() {
        List<Product> products;
        try {
            products = shopManager.getAllProducts();
            List<String> productData = new ArrayList<>();
            products.forEach(p -> productData.add("\"" +p.getProductName() + "\" by \"" + p.getBrand() + "\""));

            if (products.isEmpty()) {
                ui.showMessage("There are no products available.");
                return;
            } else {
                ui.showMessage("These are the currently available products: ");
            }

            int index = ui.showListAndGetChoice(productData, "Which product would you like to remove? ");

            if (ui.isValidIndex(index, productData.size() - 1)) {
                Product p = products.get(index - 1);
                if (ui.askForConfirmation("Are you sure you want to remove \"" + p.getProductName() + "\" by \"" + p.getBrand() + "\"?")) {
                    shopManager.removeProduct(p.getProductName());
                    ui.showMessage("\"" + p.getProductName() + "\"" + " by " + "\"" + p.getBrand()+ "\"" + " has been withdrawn from sale.\n");
                } else {
                    removeProduct();
                }
            }
        } catch (PersistenceJsonException e) {
            //Mostrar mensaje error
            throw new RuntimeException(e);
        }
    }

    private void searchProducts() {
        String productName = this.ui.askForString("Enter your query: ");
        LinkedHashMap<Product, LinkedHashMap<Shop, Double>> products = this.getDictionaryWithProductAndShopsWhereExistsWithPrice(productName);
        if (products != null) {
            this.ui.showProductSearched(products);
            int choose = this.ui.askForInteger("Which one would you like to review?");
            if (this.ui.isValidIndex(choose, products.size() - 1)) {
                int selected = this.ui.showReviewMenu("Choose an option: ");
                Product p = (Product)products.keySet().toArray()[choose - 1];
                this.searchProductInteraction(selected, p);
            }
        } else {
            this.ui.showMessage("No products found.");
        }

    }

    private LinkedHashMap<Product, LinkedHashMap<Shop, Double>> getDictionaryWithProductAndShopsWhereExistsWithPrice(String productName) {
        LinkedHashMap<Product, LinkedHashMap<Shop, Double>> products = new LinkedHashMap<>();
        List<Product> productsFound;
        try {
            productsFound = shopManager.searchProductsByQuery(productName);
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

        } catch (PersistenceJsonException e) {
            //Mostrar mensaje error
            throw new RuntimeException(e);
        }
    }

    private void addToCart(ShopProduct sp) {
        shopCart.addProductToCart(sp);
        ui.showMessage("1x \"" + sp.getProductName() + "\" by \"" + sp.getBrand() + "\" has been added to your cart.\n");
    }

    private void clearCart() {
        if (this.ui.askForConfirmation("Are you sure you want to clear your cart?")) {
            this.shopCart.clearCart();
            this.ui.showMessage("Your cart has been cleared.\n");
        }

    }

    private void checkout() {
        if (ui.askForConfirmation("Are you sure you want to checkout?")) {

            for (ShopProduct sp : shopCart.getProductList()) {
                List<Shop> shops;
                try {
                    shops = shopManager.getShopsWhereProductExistsInCatalog(sp.getProductName());
                    for (Shop s : shops){
                        if (shopManager.getProductFromShop(s.getName(), sp.getProductName()).getProductPrice() == sp.getProductPrice()) {
                            ui.showMessage("\"" + s.getName() + "\" has earned " + sp.getProductPrice() + ", for an historic total of " + s.getEarnings() + ".\n");
                            s.setEarnings(s.getEarnings() + sp.getProductPrice());
                            shopManager.checkout(s);
                        }
                    }
                } catch (PersistenceJsonException e) {
                    throw new RuntimeException(e);
                }
            }

            ui.showMessage("Your cart has been cleared.\n");
            shopCart.clearCart();
        }
    }

    private void readReviews(Product p) {
        // List reviews from product selected.
        List<Review> reviews;
        try {
            reviews = shopManager.readReviews(p.getProductName());
            if (reviews.isEmpty()) {
                ui.showMessage("There are no reviews for this product yet.");
            } else {
                ui.showMessage("These are the reviews for \"" + p.getProductName() + "\" by \"" + p.getBrand() + "\":");
                float sum = 0;
                for (Review r : reviews) {
                    sum += r.getStars();
                    ui.showMessage("\t" + r.getStars() + "* " + r.getCommentary());
                }
                ui.showMessage("Average rating: " + (sum/reviews.size()) + "\n");
            }

        } catch (PersistenceJsonException e) {
            //Mostrar mensaje error
            throw new RuntimeException(e);
        }
    }

    private void makeReviews(Product p) {
        // Make a review for the product selected.
        String stars = ui.askForString("Please rate the product (1-5 stars): ");
        String commentary = ui.askForString("Please add a comment to your review: ");
        Review review = new Review(stars, commentary);
        try {
            shopManager.makeReview(p.getProductName(), review);
        } catch (PersistenceJsonException e) {
            //Mostrar mensaje error
            throw new RuntimeException(e);
        }

        ui.showMessage("Thank you for your review of \"" + p.getProductName() + "\" by \"" + p.getBrand() + "\"");
    }

    private void exit() {
        this.ui.showMessage("We hope to see you again!");
    }
}