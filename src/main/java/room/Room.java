package room;
import java.math.BigDecimal;
import java.util.*;

public class Room {
    private Integer idRoom;  // ID de la sala, generado automáticamente por la base de datos
    private String name;     // Nombre de la sala
    private Difficulty difficulty; // Nivel de dificultad de la sala
    private BigDecimal price;      // Precio de la sala

    // ===== Constructor 1: crear nueva sala (sin id, será generado por la base de datos) =====
    public Room(String name, Difficulty difficulty, BigDecimal price) {
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
    }

    // ===== Constructor 2: cargar sala desde la base de datos (con id) =====
    public Room(Integer idRoom, String name, Difficulty difficulty, BigDecimal price) {
        this.idRoom = idRoom;
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
    }

    public String getName() {
        return name;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(idRoom, room.idRoom);}

    @Override
    public int hashCode() {
        return Objects.hash(idRoom);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", idRoom=" + idRoom +
                ", difficulty=" + difficulty +
                ", price=" + price +
                '}';
    }
}
