package Presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Bussines.Product.Product;
import Bussines.Product.ProductCategory;
import Bussines.Product.ShopProduct;

public class UI {

    private static final String MAIN_LOGO = " ________ ____\n" + " ___ / / ____/___ / __/_______\n" + "/ _ \\/ / / / __ \\/ /_/ ___/ _ \\\n" + "/ __/ / /___/ /_/ / __/ / / __/\n" + "\\___/_/\\____/\\____/_/ /_/ \\___/ \n" + "Welcome to elCofre Digital Shopping Experiences!\n";
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

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

    public void showErrorLoadingFiles() {
        System.out.println("""
                    Verifying local files...
                    Error: The products.json file can’t be accessed.
                    Shutting down...
                    """);
    }

    public String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int showProductList(List<Product> items) {
        int i = 0;
        for (i = 0; i < items.size(); i++) {
            Product sp = items.get(i);
            System.out.println((i + 1) + ") " + sp.getProductName() + " by " + sp.getBrand());
        }
        System.out.println();
        System.out.println((i + 1) + ")" + "Back\n");

        int index;
        do {
            index = askForInteger("Which one would you like to remove? ");

            if (index < 1 || index > items.size() + 1) {
                showMessage("Invalid Option");
            }
        } while (index < 1 || index > items.size() + 1);

        return index;
    }

    public void showMenu() {
        System.out.println("What would you like to do:");
        System.out.println();
        System.out.println("1) Manage Products");
        System.out.println("2) Manage Shops");
        System.out.println("3) Search Shops");
        System.out.println("4) List Shops");
        System.out.println("5) Your Cart\n");
        System.out.println("6) Exit\n");
    }

    public void showMainLogo() {
        System.out.println(MAIN_LOGO);
    }

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

    //new function
    public void showFileConfirmation() {
        System.out.println("Verifying local files...");
        System.out.println("Starting program...");
    }

    // new function
    public int showProductsMenu() {
        int option;

        System.out.println("1) Create a Product");
        System.out.println("2) Remove a Product");
        System.out.println();
        System.out.println("3) Back");
        option = askForInteger("Choose an option: ");

        return option;
    }

    //New function
    public int showShopsMenu() {
        int option;

        System.out.println("1) Create a Shop");
        System.out.println("2) Expand a Shop's Catalogue");
        System.out.println("3) Reduce a Shop's Catalogue");
        System.out.println();
        System.out.println("4) Back");
        option = askForInteger("Choose an option: ");

        return option;
    }

    //New function
    public void giveBusinessModel() {

        System.out.println("The system supports the following business models:");
        System.out.println("A) Maximum Benefits");
        System.out.println("B) Loyalty");
        System.out.println("C) Sponsored");
        System.out.println();

    }

    // New function
    public void giveProductCategory() {

        System.out.println("The system supports the following product categories:");
        System.out.println("A) General");
        System.out.println("B) Reduced Taxes");
        System.out.println("C) Superreduced taxes");
        System.out.println();
    }

    //New function
    public int showProductsInShop(List<ShopProduct> items) {
        int i = 0;
        showMessage("");
        for (i = 0; i < items.size(); i++) {
            Product sp = items.get(i);
            System.out.println("\t"+(i + 1) + ") " + sp.getProductName() + " by " + sp.getBrand());
        }
        System.out.println();
        System.out.println("\t" + (i + 1) + ")" + "Back\n");

        int index;
        do {
            index = askForInteger("Which one would you like to remove? ");

            if (index < 1 || index > items.size() + 1) {
                showMessage("Invalid Option");
            }
        } while (index < 1 || index > items.size() + 1);

        return index;
    }

    //New function
    public int askForConfirmation(String name, String brand) {
        System.out.print("Are you sure you want to remove " + "'"+name+"'" + " by " + "'"+brand+"'" + "?");
        boolean valid = false;
        while (!valid) {
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("yes") || answer.equals("no")) {
                valid = true;
                if (answer.equals("yes")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                System.out.print("Please enter 'yes' or 'no': ");
            }
        }
        return -1;
    }
}
