package org.s3team.inventoryMenu;

import org.s3team.common.valueobject.Id;
import org.s3team.common.valueobject.Name;
import org.s3team.common.valueobject.Price;
import org.s3team.inventoryService.InventoryManagementService;
import org.s3team.room.model.Difficulty;
import org.s3team.room.model.Room;
import org.s3team.theme.model.Theme;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ClassName: AddItemMenu
 * Package: org.s3team.inventoryMenu
 * Description:
 * Author: Rong Jiang
 * Create:12/12/2025 - 21:38
 * Version:v1.0
 *
 */
public class AddItemMenu {

    private final InventoryManagementService inventoryManagementService;
    private final Scanner scanner;

    public AddItemMenu(InventoryManagementService inventoryManagementService, Scanner scanner) {
        this.inventoryManagementService = inventoryManagementService;
        this.scanner = scanner;
    }

    public void addItem() {
        boolean inventoryMenuExit = false;
        int option;

        while (!inventoryMenuExit) {

            System.out.println("\n--- Add Items ---");
            System.out.println("1. Add New Room");
            System.out.println("2. Add New Clue");
            System.out.println("3. Add New Decoration");
            System.out.println("0. Go Back to Main Menu");
            System.out.println("----------------------------");

            try {
                System.out.print("Choose an option : ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (option) {
                    case 1 -> {
                        System.out.print("Enter Room Name: ");
                        String nameInput = scanner.nextLine();
                        Name name = new Name(nameInput);

                        Difficulty difficulty = null;
                        while (difficulty == null) {
                            System.out.print("Enter Difficulty (e.g., EASY, MEDIUM, HARD): ");
                            String difficultyInput = scanner.nextLine().toUpperCase();
                            try {
                                difficulty = Difficulty.valueOf(difficultyInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Invalid difficulty level. Please use EASY, MEDIUM, or HARD.");
                            }
                        }

                        System.out.print("Enter Price (e.g., 99.99): ");
                        BigDecimal priceAmount = scanner.nextBigDecimal();
                        scanner.nextLine();
                        Price price = new Price(priceAmount);

                        System.out.print("Enter Theme ID: ");
                        int themeIdValue = scanner.nextInt();
                        scanner.nextLine();
                        Id<Theme> themeId = new Id<>(themeIdValue);
                        Room room = Room.createNew(name,difficulty,price,themeId);
                        inventoryManagementService.addRoom(room);
                    }
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

}
