package room;

import common.valueobject.Id;
import common.valueobject.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class RoomService {
    private RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void save(Room room) {
        roomDAO.save(room);

    }

    public Room findById(Id id) {
        Optional<Room> roomOptional = roomDAO.findById(id);

        return roomOptional.orElseThrow(
                () -> new RuntimeException("Room with ID " + id.value() + " not found.")
        );
    }

    public List findAll() {
        return roomDAO.findAll();
    }

    public boolean update(Room room) {
        if (roomDAO.findById(room.getRoomId()).isEmpty()) {
            return false;
        }
        return roomDAO.update(room);
    }

    public boolean delete(Id id) {
        return roomDAO.delete(id);
    }

    public int count() {
        return roomDAO.count();

    }

    public Price calculateTotalPrice() {
        return roomDAO.calculateTotalPrice();
    }

}
