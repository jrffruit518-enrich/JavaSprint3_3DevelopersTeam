package org.s3team.inventoryMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ClassName: InventoryMenu
 * Package: org.s3team.inventoryMenu
 * Description:
 * Author: Rong Jiang
 * Create:12/12/2025 - 21:38
 * Version:v1.0
 *
 */
public class InventoryMenu {
    private final AddItemMenu addItemMenu;
    private final RemoveItemMenu removeItemMenu;
    private final DisplayInvetoryMenu displayInventoryMenu;
    private final DisplayInventoryValueMenu displayInventoryValueMenu;
    private final DisplayInventoryQuantityMenu displayInventoryQuantityMenu;
    private final Scanner scanner;

    public InventoryMenu(AddItemMenu addItemMenu, RemoveItemMenu removeItemMenu, DisplayInvetoryMenu displayInventoryMenu, DisplayInventoryValueMenu displayInventoryValueMenu, DisplayInventoryQuantityMenu displayInventoryQuantityMenu, Scanner scanner) {
        this.addItemMenu = addItemMenu;
        this.removeItemMenu = removeItemMenu;
        this.displayInventoryMenu = displayInventoryMenu;
        this.displayInventoryValueMenu = displayInventoryValueMenu;
        this.displayInventoryQuantityMenu = displayInventoryQuantityMenu;
        this.scanner = scanner;
    }


    public void inventoryMenuStart() {

        boolean inventoryMenuExit = false;
        int option;

        while (!inventoryMenuExit) {

            System.out.println("\n--- INVENTORY MANAGEMENT ---");
            System.out.println("1. Add New Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display Inventory");
            System.out.println("4. Display Inventory Total Value");
            System.out.println("5. Display Inventory Total Quantity");
            System.out.println("0. Go Back to Main Menu");
            System.out.println("----------------------------");

            try {
                System.out.print("Choose an option for Inventory Management: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (option) {
                    case 1 -> addItemMenu.addItem();
                    case 2 -> removeItemMenu.removeItem();
                    case 3 -> displayInventoryMenu.displayInventory();
                    case 4 -> displayInventoryValueMenu.displayInventoryValue();

                    case 5 -> displayInventoryQuantityMenu.displayInventoryQuantity();
                    case 0 ->{
                        System.out.println("Returning to Main Menu...");
                    inventoryMenuExit = true;
                    }
                    default -> System.out.println("❌ Invalid option. Please choose a number from 0 to 5.");

                }

            } catch (InputMismatchException e) {
                System.out.println("⛔ Input Error: Please enter a valid number.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }

}
