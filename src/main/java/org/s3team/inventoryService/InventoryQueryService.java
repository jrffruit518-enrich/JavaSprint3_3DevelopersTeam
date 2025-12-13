package org.s3team.inventoryService;

import org.s3team.Exceptions.ClueNotFoundException;
import org.s3team.clue.model.Clue;
import org.s3team.clue.service.ClueService;
import org.s3team.common.valueobject.Id;
import org.s3team.common.valueobject.Price;
import org.s3team.decoration.model.Decoration;
import org.s3team.decoration.service.DecorationService;
import org.s3team.room.Service.RoomService;
import org.s3team.room.model.Room;

import java.math.BigDecimal;
import java.util.List;

public class InventoryQueryService {
    private ClueService clueService;
    private DecorationService decorationService;
    private RoomService roomService;

    public InventoryQueryService(ClueService clueService, DecorationService decorationService, RoomService roomService) {
        this.clueService = clueService;
        this.decorationService = decorationService;
        this.roomService = roomService;
    }


    public Room findRoomById(Id<Room> id) {
        return roomService.findById(id);
    }

    public List<Room> listRoom() {
        return roomService.findAll();
    }


    public int countRoom() {
        return roomService.count();
    }

    public Price calculateRoomTotalPrice() {
        return roomService.calculateTotalPrice();
    }


    public Clue getClueById(Id<Clue> id) {
        return clueService.getClueById(id).orElseThrow(() ->
                new ClueNotFoundException("Clue with ID " + id.value() + " not found.")
        );
    }

    public List<Clue> listClue() {
        return clueService.getAllClues();
    }


    public int countClue() {
        return clueService.count();
    }

    public Price calculateClueTotalPrice() {
        return clueService.calculateTotalPrice();
    }


    public Decoration findDecoracionById(Id<Decoration> id) {
        return decorationService.findDecoracionById(id);
    }

    public List<Decoration> listDecoracion() {
        return decorationService.getAllDecorations();
    }


    public int countDecoracion() {
        return decorationService.countDecoracion();
    }

    public Price calculateDecoracionTotalPrice() {
        return decorationService.calculateDecoracionTotalPrice();
    }

    public int countInventory() {
        int roomNumber = roomService.count();
        int clueNumber = clueService.count();
        int decoracionNumber = decorationService.countDecoracion();
        int totalInventoryNumber = roomNumber + clueNumber + decoracionNumber;

        // Print statements to display inventory counts
        System.out.println("--- Inventory Count Summary ---");
        System.out.println("Number of Rooms: " + roomNumber);
        System.out.println("Number of Clues: " + clueNumber);
        System.out.println("Number of Decorations: " + decoracionNumber);
        System.out.println("-------------------------------");
        System.out.println("Total Inventory Count: " + totalInventoryNumber);

        return totalInventoryNumber;
    }

    public Price calculateTotalPrice() {
        Price roomTotalPrice = roomService.calculateTotalPrice();
        Price clueTotalPrice = clueService.calculateTotalPrice();
        Price decorationTotalPrice = decorationService.calculateDecoracionTotalPrice();

        BigDecimal roomAmount = roomTotalPrice.value();
        BigDecimal clueAmount = clueTotalPrice.value();
        BigDecimal decorationAmount = decorationTotalPrice.value();

        BigDecimal totalAmount = roomAmount.add(clueAmount).add(decorationAmount);

        Price totalInventoryPrice = new Price(totalAmount);

        System.out.println("--- Inventory Price Summary ---");
        System.out.println("Room Total Price: " + roomAmount);
        System.out.println("Clue Total Price: " + clueAmount);
        System.out.println("Decoration Total Price: " + decorationAmount);
        System.out.println("---------------------------------");
        System.out.println("Total Inventory Price: " + totalAmount);

        return totalInventoryPrice;

    }


}
