package room;
import java.math.BigDecimal;
import java.util.*;

public class Room {
    private Integer roomId;  // ID de la sala, generado automáticamente por la base de datos
    private String name;     // Nombre de la sala
    private Difficulty difficulty; // Nivel de dificultad de la sala
    private BigDecimal price;// Precio de la sala
    private int themeId;

    // ===== Constructor 1: crear nueva sala (sin id, será generado por la base de datos) =====
    public Room(String name, Difficulty difficulty, BigDecimal price,int themeId) {
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.themeId = themeId;
    }

    // ===== Constructor 2: cargar sala desde la base de datos (con id) =====
    public Room(Integer roomId, String name, Difficulty difficulty, BigDecimal price,int themeId) {
        this.roomId = roomId;
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.themeId = themeId;
    }


    public String getName() {
        return name;
    }

    public Integer getRoomId() {
        return roomId
                ;
    }

    public void setRoomId(Integer roomId
    ) {
        this.roomId
                = roomId
        ;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getThemeId() {
        return themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId
                , room.roomId
        );}

    @Override
    public int hashCode() {
        return Objects.hash(roomId
        );
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", price=" + price +
                ", themeId=" + themeId +
                '}';
    }
}
