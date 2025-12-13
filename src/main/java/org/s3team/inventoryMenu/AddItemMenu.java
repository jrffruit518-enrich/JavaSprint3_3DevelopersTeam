package org.s3team.inventoryMenu;

import org.s3team.clue.model.Clue;
import org.s3team.clue.model.ClueDescription;
import org.s3team.clue.model.ClueType;
import org.s3team.common.util.ConsoleInput;
import org.s3team.common.valueobject.Id;
import org.s3team.common.valueobject.Name;
import org.s3team.common.valueobject.Price;
import org.s3team.decoration.model.Decoration;
import org.s3team.decoration.model.Material;
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
                        System.out.println("--- 🚪 Adding New Room ---");

                        String nameInput = ConsoleInput.readLine("Enter Room Name: ");
                        Name name = new Name(nameInput);

                        Difficulty difficulty = null;
                        while (difficulty == null) {
                            String difficultyInput = ConsoleInput.readLine("Enter Difficulty (EASY, MEDIUM, HARD): ").toUpperCase();
                            try {
                                difficulty = Difficulty.valueOf(difficultyInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Invalid difficulty level. Please use EASY, MEDIUM, or HARD.");
                            }
                        }

                        BigDecimal priceAmount = ConsoleInput.readBigDecimal("Enter Price (e.g., 99.99): ");
                        Price price = new Price(priceAmount);

                        int themeIdValue = ConsoleInput.readInt("Enter Theme ID: ");
                        Id<Theme> themeId = new Id<>(themeIdValue);

                        try {
                            Room room = Room.createNew(name, difficulty, price, themeId);

                            Room savedRoom = inventoryManagementService.addRoom(room);

                            System.out.println("✅ Room added successfully!");
                            System.out.println(savedRoom);

                        } catch (RuntimeException e) {
                            System.err.println("❌ Failed to add room: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("--- 🔑 Adding New Clue ---");


                        ClueType type = null;
                        while (type == null) {
                            String typeInput = ConsoleInput.readLine("Enter Clue Type (TEXT, OBJECT, SOUND): ").toUpperCase();
                            try {
                                type = ClueType.valueOf(typeInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Invalid clue type. Please use TEXT, OBJECT, or SOUND.");
                            }
                        }

                        String descriptionInput = ConsoleInput.readLine("Enter Clue Description: ");
                        ClueDescription description = new ClueDescription(descriptionInput);

                        BigDecimal priceAmount = ConsoleInput.readBigDecimal("Enter Price (e.g., 5.00): ");
                        Price price = new Price(priceAmount);

                        int themeIdValue = ConsoleInput.readInt("Enter Theme ID for the clue: ");
                        Id<Theme> themeId = new Id<>(themeIdValue);

                        int roomIdValue = ConsoleInput.readInt("Enter Room ID the clue belongs to: ");
                        Id<Room> roomId = new Id<>(roomIdValue);

                        try {

                            Clue savedClue = inventoryManagementService.addClue(
                                    type,
                                    description,
                                    price,
                                    themeId,
                                    roomId
                            );

                            System.out.println("✅ Clue added successfully!");
                            System.out.println(savedClue);

                        } catch (RuntimeException e) {
                            // 捕获所有运行时异常，包括 ClueDescription 的验证异常和 Service 层的 RoomNotFoundException
                            System.err.println("❌ Failed to add clue: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.println("--- 🖼️ Adding New Decoration Object ---");

                        // 1. 获取 Name
                        String nameInput = ConsoleInput.readLine("Enter Decoration Name: ");

                        // 2. 获取 Material (需要手动验证枚举类型)
                        Material material = null;
                        while (material == null) {
                            String materialInput = ConsoleInput.readLine("Enter Material (WOOD, METAL, PLASTIC): ").toUpperCase();
                            try {
                                material = Material.valueOf(materialInput);
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Invalid material. Please use WOOD, METAL, or PLASTIC.");
                            }
                        }

                        // 3. 获取 Stock (使用 ConsoleInput.readInt)
                        int stockValue = ConsoleInput.readInt("Enter Stock Quantity: ");

                        // 4. 获取 Price (使用 ConsoleInput.readBigDecimal)
                        BigDecimal priceAmount = ConsoleInput.readBigDecimal("Enter Price (e.g., 10.50): ");

                        // 5. 获取 Room ID (使用 ConsoleInput.readInt)
                        int roomIdValue = ConsoleInput.readInt("Enter Target Room ID: ");

                        try {
                            // 创建 Decoration 实体
                            // 使用您提供的 Decoration 构造函数 (String name, Material material, int stock, BigDecimal price, int roomId)
                            Decoration newDecoration = new Decoration(
                                    nameInput,
                                    material,
                                    stockValue,
                                    priceAmount,
                                    roomIdValue
                            );

                            // 调用 Service 层进行业务校验和持久化
                            inventoryManagementService.addDecoracion(newDecoration);

                            // 成功反馈（Service 内部也会打印 SUCCESS 消息）
                            System.out.println("✅ Decoration item creation request processed.");

                        } catch (IllegalArgumentException e) {
                            // 捕获 Service 层抛出的业务规则异常（即 RULE VIOLATION: 材质不匹配）
                            System.err.println("❌ Failed to add decoration: " + e.getMessage());
                        } catch (RuntimeException e) {
                            // 捕获其他运行时异常（例如，DataBaseConnectionException 或 DAO 打印的 ERROR 消息）
                            System.err.println("❌ An unexpected error occurred during saving: " + e.getMessage());
                        }
                    }

                    case 0 -> {
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


