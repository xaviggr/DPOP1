package Presentation;

import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Review;
import Bussines.Shop;
import Bussines.ShopCart;
import Bussines.ShopManager;
import Persistence.exception.PersistenceJsonException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Controlador que gestiona la interacción entre la interfaz de usuario (UI),
 * el gestor de tiendas (ShopManager) y el carrito de compras (ShopCart).
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class Controller {

    private final UI ui;
    private final ShopManager shopManager;

    private final ShopCart shopCart;
    /**
     * Constructor de la clase Controller.
     *
     * @param ui           Interfaz de usuario.
     * @param shopManager  Gestor de tiendas.
     * @param shopCart     Carrito de compras.
     */
    public Controller(UI ui, ShopManager shopManager, ShopCart shopCart) {
        this.ui = ui;
        this.shopManager = shopManager;
        this.shopCart = shopCart;
    }

    /**
     * Método principal que inicia la ejecución del programa.
     */
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

    /**
     * Realiza la verificación necesaria antes de ejecutar el programa.
     *
     * @throws FileNotFoundException Si no se encuentran archivos necesarios.
     */
    private void checkToRun() throws FileNotFoundException {
        shopManager.checkIfFileExists();
    }

    /**
     * Inicia el menú principal del programa.
     */
    private void startMenu() {
        int option;

        do {
            ui.showMenu(); // Display the main menu

            option = ui.askForInteger("Choose a Digital Shopping Experience: "); // Ask the user for the chosen option
            executeOption(option); // Execute the selected option
        } while(option != 6); // Continue until the exit option is selected
    }
    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param option Opción seleccionada por el usuario.
     */
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
                ui.showCart(shopCart);
                choose = ui.showCartMenu();
                shopCartInteraction(choose);
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
    /**
     * Realiza la interacción relacionada con las tiendas según la opción seleccionada.
     *
     * @param choose Opción seleccionada por el usuario.
     */
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

    /**
     * Realiza la interacción relacionada con la búsqueda de productos.
     *
     *      @param choose Opción seleccionada por el usuario.
     *      @param p      Producto asociado a la interacción.
     */
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
    /**
     * Realiza la interacción relacionada con los productos según la opción seleccionada.
     *
     * @param choose Opción seleccionada por el usuario.
     */
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
    /**
     * Realiza la interacción relacionada con la lista de tiendas según la opción seleccionada.
     *
     * @param choose Opción seleccionada por el usuario.
     * @param p      Producto asociado a la interacción.
     */
    private void listShopsInteraction(int choose, ShopProduct p) {

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
                addToCart(p);
                break;
            case 4:
                //Back
                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }
    /**
     * Realiza la interacción relacionada con el carrito de compras según la opción seleccionada.
     *
     * @param choose Opción seleccionada por el usuario.
     */
    private void shopCartInteraction(int choose) {

        switch (choose) {
            case 1:
                ui.showMessage("");
                checkout();
                break;
            case 2:
                ui.showMessage("");
                clearCart();
                break;
            case 3:
                //Back
                break;
            default:
                ui.showMessage("Invalid option.");
                break;
        }
    }

    //SHOPS
    /**
     * Realiza la interacción relacionada con la creación de una tienda.
     */
    private void createShop() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        String description = ui.askForString("Please enter the shop's description: ");
        int foundingYear = ui.askForPositiveInteger("Please enter the shop's founding year: ");
        String businessModel = ui.askForShopModel();

        Shop s = new Shop(shopName,description,foundingYear, 0,businessModel,null);
        try {
            shopManager.createShop(s);
        } catch (PersistenceJsonException e) {
            //Mensaje de error
            throw new RuntimeException(e);
        }
        ui.showMessage("'" + shopName + "'"  + " is now a part of the elCofre family.\n");
    }
    /**
     * Realiza la interacción relacionada con la expansión del catálogo de una tienda.
     */
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
                ShopProduct sp = new ShopProduct(p.getProductName(),p.getBrand(),p.getMaxPrice(),p.getCategory(),price);
                shopManager.expandCatalog(shopName, sp);
                ui.showMessage("'" + p.getProductName() +"'" + " by " + "'"+p.getBrand()+"'" + " is now being sold at " + "'"+shopName+"'"+".\n");
            }

        } catch (PersistenceJsonException e) {
            //Mensaje de error
            throw new RuntimeException(e);
        }
    }
    /**
     * Realiza la interacción relacionada con la reducción del catálogo de una tienda.
     */
    private void reduceCatalog() {
        String shopName = ui.askForString("Please enter the shop's name: ");
        Shop s;
        try {
            s = shopManager.findShopByName(shopName);

            if (s == null) {
                ui.showMessage("This shop doesn't exists.");
                return;
            }

            List<String> shopProductsNames = shopManager.getAllProductsNameFromShop(shopName);
            ui.showMessage("This shop sells the following products:");
            int index = ui.showListAndGetChoice(shopProductsNames, "Which one would you like to remove? ");
            if (ui.isValidIndex(index, shopProductsNames.size() - 1)) {
                String productName = shopProductsNames.get(index - 1);
                shopManager.reduceCatalog(shopName, productName);
                ui.showMessage("\"" + productName + "\"" + " is no longer being sold at " + "\"" + shopName + "\".\n");

            }

        } catch (PersistenceJsonException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Realiza la interacción relacionada con la lista de tiendas.
     */
    private void listShops() {
        List<String> shopNames;
        try {
            shopNames = shopManager.getAllNameShops();
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

        } catch (PersistenceJsonException e) {
            throw new RuntimeException(e);
        }
    }

    //PRODUCTS
    /**
     * Realiza la interacción relacionada con la creación de un producto.
     */
    private void createProduct() {
        String name = ui.askForString("Please enter the product’s name: ");
        String brand = ui.askForString("Please enter the product’s brand: ");
        double maxPrice = ui.askForPositiveDouble("Please enter the product’s maximum retail price: ");
        ProductCategory category = askForProductCategory();

        try {
            if(shopManager.findProduct(name) != null) {
                ui.showMessage("Error. That product already exists.\n");
                return;
            }
            shopManager.createProduct(name, brand, maxPrice, category);
            ui.showMessage("The product " + "\"" + name + "\"" + " by \"" + brand + "\" was added to the system.\n");

        } catch (PersistenceJsonException e) {
            //Mensaje de error
            throw new RuntimeException(e);
        }
    }
    /**
     * Realiza la interacción relacionada con la eliminación de un producto.
     */
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
    /**
     * Realiza la interacción relacionada con la búsqueda de productos.
     */
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

    /**
     * Obtiene un diccionario que mapea cada producto encontrado por la búsqueda
     * con las tiendas donde está disponible y sus respectivos precios.
     *
     * @param productName Nombre del producto a buscar.
     * @return Un diccionario que asocia cada producto con un subdiccionario que
     *         mapea cada tienda donde está disponible con su precio.
     *         Si no se encuentra ningún producto, devuelve null.
     */
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

    //CART
    private void addToCart(ShopProduct sp) {
        shopCart.addProductToCart(sp);
        ui.showMessage("1x \"" + sp.getProductName() + "\" by \"" + sp.getBrand() + "\" has been added to your cart.\n");
    }
    /**
     * Realiza la interacción relacionada con la limpieza del carrito de compras.
     */
    private void clearCart() {
        if (ui.askForConfirmation("Are you sure you want to clear your cart?")) {
            shopCart.clearCart();
            ui.showMessage("Your cart has been cleared.\n");
        }
    }
    /**
     * Realiza la interacción relacionada con el proceso de pago y vacía el carrito de compras.
     */
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

    //REVIEWS
    /**
     * Realiza la interacción relacionada con las revisiones de un producto.
     *
     * @param p Producto asociado a las revisiones.
     */
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
    /**
     * Realiza la interacción relacionada con la creación de revisiones de un producto.
     *
     * @param p Producto asociado a la revisión.
     */
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

    /**
     * Solicita al usuario la categoría de un producto.
     *
     * @return La categoría de producto seleccionada por el usuario.
     */
    public ProductCategory askForProductCategory() {
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

    //EXIT
    /**
     * Realiza la interacción relacionada con la salida del programa.
     */
    private void exit() {
        ui.showMessage("We hope to see you again!");
    }
}