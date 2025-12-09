package inventory;

import decoracion.ClueDAO;
import decoracion.DecorationDAO;
import room.Room;
import decoracion.Clue;
import decoracion.Decoration_object;
import room.RoomDAO;
import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: InventaryService
 * Package: inventory
 * Description:
 * Author: Rong Jiang
 * Create:08/12/2025 - 23:25
 * Version:v1.0
 *
 */

/*InventoryService
 ├── addRoom()
 ├── addClue()
 ├── addDecoration()
 ├── removeRoom()
 ├── removeClue()
 ├── removeDecoration()
 ├── listInventory()
 └── calculateTotalValue()*/


public class InventaryService {

        private RoomDAO roomDAO;
        private ClueDAO clueDAO;
        private DecorationDAO decorationDAO;


    public InventoryService(RoomDAO roomDAO, ClueDAO clueDAO, DecorationDAO decorationDAO) {
        this.roomDAO = roomDAO;
        this.clueDAO = clueDAO;
        this.decorationDAO = decorationDAO;
    }

    public void showInventory() {
        int rooms = roomDAO.count();
        int clues = clueDAO.count();
        int decorations = decorationDAO.count();

        System.out.println("===== INVENTARIO =====");
        System.out.println("Salas disponibles: " + rooms);
        System.out.println("Pistas disponibles: " + clues);
        System.out.println("Objetos de decoración: " + decorations);
        System.out.println("=======================");
    }

        public void addRoom(Room room) { roomDAO.add(room); }
        public void addClue(Clue clue) { clueDAO.add(clue); }
        public void addDecoration(Decoration decoration) { decorationDAO.add(decoration); }

        public void removeRoom(int id) { roomDAO.delete(id); }
        public void removeClue(int id) { clueDAO.delete(id); }
        public void removeDecoration(int id) { decorationDAO.delete(id); }

        public List<Room> getAllRooms() { return roomDAO.findAll(); }
        public List<Clue> getAllClues() { return clueDAO.findAll(); }
        public List<Decoration_object> getAllDecorations() { return decorationDAO.findAll(); }

        public BigDecimal getTotalInventoryValue() {
        return null;
        }

}
