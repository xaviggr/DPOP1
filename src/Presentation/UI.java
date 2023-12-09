package Presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    private static final String MAIN_LOGO = " ________ ____\n" + " ___ / / ____/___ / __/_______\n" + "/ _ \\/ / / / __ \\/ /_/ ___/ _ \\\n" + "/ __/ / /___/ /_/ / __/ / / __/\n" + "\\___/_/\\____/\\____/_/ /_/ \\___/ \n" + "Welcome to elCofre Digital Shopping Experiences.!\n";

    private Scanner scanner;

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

    public void showList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("\t* " + items.get(i));
        }
    }

    public void showMenu() {
        System.out.println("Verifying local files...");
        System.out.println("Starting program...");
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
}
