package room;

import common.valueobject.Id;
import common.valueobject.Name;
import common.valueobject.Price;

import java.math.BigDecimal;

/**
 * ClassName: Main
 * Package: room
 * Description:
 * Author: Rong Jiang
 * Create:10/12/2025 - 21:42
 * Version:v1.0
 *
 */
public class Main {
    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAO();
        RoomService roomService = new RoomService(roomDAO);
        Room roomFeliz = Room.createNew(new Name("Feliz"),Difficulty.EASY,new Price(new BigDecimal("120")),new Id<Theme>(2));
        Room roomTerror = Room.createNew(new Name("Terror"),Difficulty.EXTREME,new Price(new BigDecimal("320")),new Id<Theme>(1));
        Room roomPuzzle = Room.createNew(new Name("Puzzle"),Difficulty.MEDIUM,new Price(new BigDecimal("180")),new Id<Theme>(3));

        roomService.save(roomFeliz);
        roomService.save(roomTerror);
        roomService.save(roomPuzzle);

        roomService.findAll().stream().forEach(System.out::println);
        System.out.println(roomService.findById(new Id<Room>(1)));

        Room roomFelizUpdate = Room.createNew(new Name("Happy"),Difficulty.EASY,new Price(new BigDecimal("120")),new Id<Theme>(2));
        roomService.update(roomFelizUpdate);

        System.out.println(roomService.count());
        System.out.println(roomService.calculateTotalPrice());
        roomService.delete(new Id<Room>(2));
        roomService.findAll().stream().forEach(System.out::println);
    }
}
