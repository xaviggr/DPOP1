package Presentation;

import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;
import Bussines.Shop;
import Bussines.ShopCart;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona la interfaz de usuario (UI) para interactuar con el sistema.
 */
@SuppressWarnings("SpellCheckingInspection")
public class UI {

    private static final String MAIN_LOGO = """
                    __           ____       \s
              ___  / /________  / __/_______\s
             / _ \\/ / ___/ __ \\/ /_/ ___/ _ \\
            /  __/ / /__/ /_/ / __/ /  /  __/
            \\___/_/\\___/\\____/_/ /_/   \\___/\s
                                            \s
            """;
    private final Scanner scanner;

    /**
     * Constructor que inicializa el objeto Scanner para la entrada del usuario.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    //ASK METHODS
    /**
     * Solicita al usuario un número entero.
     *
     * @param message El mensaje que se muestra al usuario.
     * @return El número entero ingresado por el usuario.
     */
    public int askForInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This isn't an integer!");
            } finally {
                // Consume the newline character to clear the buffer
                scanner.nextLine();
            }
        }
    }
    /**
     * Solicita al usuario una cadena de texto.
     *
     * @param message El mensaje que se muestra al usuario.
     * @return La cadena de texto ingresada por el usuario.
     */
    public String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    /**
     * Solicita al usuario un número decimal (double).
     *
     * @param message El mensaje que se muestra al usuario.
     * @return El número decimal ingresado por el usuario.
     */
    public double askForDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("This isn't a double!");
            } finally {
                // Consume the newline character to clear the buffer
                scanner.nextLine();
            }
        }
    }
    /**
     * Solicita al usuario confirmación (Sí/No).
     *
     * @param message El mensaje que se muestra al usuario.
     * @return true si la respuesta es "Sí", false si la respuesta es "No".
     */
    public boolean askForConfirmation(String message) {
        System.out.print(message);
        while (true) {
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("yes") || answer.equals("no")) {
                return answer.equals("yes");
            } else {
                System.out.print("Please enter 'yes' or 'no': ");
            }
        }
    }
    /**
     * Solicita al usuario el modelo de negocio para una tienda.
     *
     * @return El modelo de negocio seleccionado por el usuario.
     */
    public String askForShopModel() {
        String userInput;
        boolean isValid = false;
        showMessage("");
        giveBusinessModel();
        do {
            userInput = askForString("Please pick the shop’s business model: ").toUpperCase();

            if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C")) {
                isValid = true;
            } else {
                showMessage("Please enter a valid option (A, B, or C).");
            }
        } while (!isValid);

        return switch (userInput) {
            case "A" -> "Maximum Benefits";
            case "B" -> "Loyalty";
            case "C" -> "Sponsored";
            default -> "Invalid Option";
        };

    }
    /**
     * Solicita al usuario la categoría de un producto.
     *
     * @return La categoría de producto seleccionada por el usuario.
     */
    public ProductCategory askForProductCategory() {
        String userInput;

        boolean isValid = false;
        showMessage("");
        giveProductCategory();
        do {
            userInput = askForString("Please pick the shop’s business model: ").toUpperCase();

            if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C")) {
                isValid = true;
            } else {
                showMessage("Please enter a valid option (A, B, or C).");
            }
        } while (!isValid);

        return switch (userInput) {
            case "A" -> ProductCategory.GENERAL;
            case "B" -> ProductCategory.REDUCED_TAXES;
            case "C" -> ProductCategory.SUPER_REDUCED_TAXES;
            default -> ProductCategory.valueOf("Invalid option");
        };
    }

    //VALIDATION METHODS
    /**
     * Verifica si un índice dado es válido para una lista de cierto tamaño.
     *
     * @param index El índice a verificar.
     * @param size  El tamaño de la lista.
     * @return true si el índice es válido, false de lo contrario.
     */
    public boolean isValidIndex(int index, int size) {
        return index >= 1 && index <= size + 1;
    }

    //GENERAL METHODS
    /**
     * Muestra una lista de elementos.
     *
     * @param items La lista de elementos a mostrar.
     */
    public void showList(List<String> items) {
        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                String item = items.get(i);
                System.out.println((i + 1) + ") " + item);
            }
            System.out.println();
        }
    }
    /**
     * Muestra una lista de opciones y obtiene la elección del usuario.
     *
     * @param options La lista de opciones.
     * @param message El mensaje que se muestra al usuario.
     * @return La elección del usuario.
     */
    public int showListAndGetChoice(List<String> options, String message) {
        if (!options.isEmpty()) {
            showList(options);
            System.out.println((options.size() + 1) + ") Back");
            return askForInteger(message);
        }
        return -1;
    }

    //SHOW MENUS
    /**
     * Muestra el menú principal.
     */
    public void showMenu() {
        List<String> menuOptions = List.of(
                "Manage Products",
                "Manage Shops",
                "Search Products",
                "List Shops",
                "Your Cart",
                "Exit"
        );
        showList(menuOptions);
    }
    /**
     * Muestra el menú de operaciones relacionadas con tiendas.
     *
     * @return La elección del usuario en el menú de tiendas.
     */
    public int showShopsMenu() {
        List<String> shopMenuOptions = List.of(
                "Create a Shop",
                "Expand a Shop's Catalogue",
                "Reduce a Shop's Catalogue"
        );
        return showListAndGetChoice(shopMenuOptions, "Choose an option: ");
    }
    /**
     * Muestra el menú de operaciones relacionadas con productos.
     *
     * @return La elección del usuario en el menú de productos.
     */
    public int showProductsMenu() {
        List<String> productMenuOptions = List.of(
                "Create a Product",
                "Remove a Product"
        );
        return showListAndGetChoice(productMenuOptions, "Choose an option: ");
    }

    //SHOW MESSAGES / DATA
    /**
     * Muestra el logotipo principal.
     */
    public void showMainLogo() {
        System.out.println(MAIN_LOGO);
    }
    /**
     * Muestra un mensaje de error al cargar archivos.
     */
    public void showErrorLoadingFiles() {
        System.out.println("""
                    Verifying local files...
                    Error: The products.json file can’t be accessed.
                    Shutting down...
                    """);
    }
    /**
     * Muestra un mensaje de confirmación de carga de archivos.
     */
    public void showFileConfirmation() {
        System.out.println("Verifying local files...");
        System.out.println("Starting program...\n");
    }
    /**
     * Muestra un mensaje genérico al usuario.
     *
     * @param message El mensaje a mostrar.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    //SHOW DATA
    /**
     * Muestra información detallada sobre los productos buscados.
     *
     * @param products Un mapa que asocia productos con tiendas y precios.
     */
    public void showProductSearched(LinkedHashMap<Product, LinkedHashMap<Shop, Double>> products) {
        if (!products.isEmpty()) {
            for (int i = 0; i < products.size(); i++) {
                Product p = (Product) products.keySet().toArray()[i];
                System.out.println("\t" + (i + 1) + ") \"" + p.getProductName() + "\" by \"" + p.getBrand() + "\"");
                if (products.get(p).keySet().isEmpty()) {
                    System.out.println("\t\tThis product is not currently being sold in any shops.");
                } else {
                    for (Shop s: products.get(p).keySet()) {
                        System.out.println("\t\t\t - " + s.getName() + ": " + products.get(p).get(s));
                    }
                }
                System.out.println();
            }
            System.out.println("\n\t" + (products.size() + 1) + ") " + " Back");
        }
    }
    /**
     * Muestra los modelos de negocio disponibles.
     */
    public void giveBusinessModel() {

        System.out.println("The system supports the following business models:");
        System.out.println("A) Maximum Benefits");
        System.out.println("B) Loyalty");
        System.out.println("C) Sponsored");
        System.out.println();

    }
    /**
     * Muestra las categorías de productos disponibles.
     */
    public void giveProductCategory() {

        System.out.println("The system supports the following product categories:");
        System.out.println("A) General");
        System.out.println("B) Reduced Taxes");
        System.out.println("C) Superreduced taxes");
        System.out.println();
    }
    /**
     * Muestra un menú de revisiones.
     *
     * @param message El mensaje a mostrar al usuario.
     * @return La elección del usuario en el menú de revisiones.
     */
    public int showReviewMenu(String message) {
        List<String> options = List.of("Read Reviews", "Review Product");
        return showListAndGetChoice(options, message);
    }
    /**
     * Muestra un menú del catálogo.
     *
     * @param message El mensaje a mostrar al usuario.
     * @return La elección del usuario en el menú del catálogo.
     */
    public int showCatalogMenu(String message) {
        List<String> options = List.of("Read Reviews", "Review Product", "Add to Cart");
        return showListAndGetChoice(options, message);
    }
    /**
     * Muestra el menú del carrito de compras.
     *
     * @return La elección del usuario en el menú del carrito de compras.
     */
    public int showCartMenu() {
        List<String> options = List.of("Checkout", "Clear cart");
        return showListAndGetChoice(options, "Choose an option: ");
    }
    /**
     * Muestra el catálogo de una tienda.
     *
     * @param s        La tienda.
     * @param products La lista de productos de la tienda.
     */
    public void showShopCatalogue(Shop s, List<ShopProduct> products) {
        if (!products.isEmpty()) {
            System.out.println(s.getName() + " - Since" + s.getFoundationYear());
            System.out.println(s.getDescription());
            for (int i = 0; i < products.size(); i++) {
                ShopProduct sp = products.get(i);
                System.out.println("\t" + (i + 1) + ") " + sp.getProductName() + " by " + sp.getBrand());
                System.out.println("\t\t\t - " + sp.getProductPrice());
            }
            System.out.println();
            System.out.println("\t" + (products.size() + 1) + ")" + "Back\n");
        }
    }
    /**
     * Muestra el contenido del carrito de compras.
     *
     * @param shopCart El carrito de compras.
     */
    public void showCart(ShopCart shopCart) {
        if (!shopCart.getProductList().isEmpty()) {
            System.out.println("Your cart contains the following items:");
            for (int i = 0; i < shopCart.getProductList().size(); i++) {
                ShopProduct sp = shopCart.getProductList().get(i);
                System.out.println("\t\t - \"" + sp.getProductName() + "\" by \"" + sp.getBrand() + "\"");
                System.out.println("\t\t\tPrice: " + sp.getProductPrice() + "\n");
            }

            System.out.println("Total: " + shopCart.getTotalPrice());
        } else {
            System.out.println("Your cart is empty.");
        }
    }
}
