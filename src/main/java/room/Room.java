package room;
import common.valueobject.*;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {
    private Id<Room> roomId;  // Id de la sala, generado automáticamente por la base de datos
    private Name name;     // Nombre de la sala
    private Difficulty difficulty; // Nivel de dificultad de la sala
    private Price price;// Precio de la sala
    private Id<Theme> themeId;

    protected Room() {
    }

    private Room(Id<Room> roomId, Name name, Difficulty difficulty, Price price, Id<Theme> themeId) {
        this.roomId = roomId;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.themeId = themeId;
    }

    // ==========================================================
    // 静态工厂方法 (Static Factory Methods)
    // ==========================================================

    /**
     * 【DDD 工厂方法 1】用于创建新的、尚未持久化的 Room 实体。
     * （实体还没有 ID，数据库会在保存时生成）
     */
    public static Room createNew(Name name, Difficulty difficulty, Price price, Id<Theme> themeId) {
        // 在这里可以进行创建前的业务校验
        return new Room(null, name, difficulty, price, themeId);
    }

    /**
     * 【DDD 工厂方法 2】用于从数据库加载数据时重建（水合）已存在的 Room 实体。
     * （用于 DAO 层从 ResultSet 中映射数据）
     */
    public static Room rehydrate(Id<Room> roomId, Name name, Difficulty difficulty, Price price, Id<Theme> themeId) {
        // 在这里可以进行重建时的校验，例如确保 ID 不为 null
        if (roomId == null) {
            throw new IllegalArgumentException("Rehydrating entity must have a valid ID.");
        }
        return new Room(roomId, name, difficulty, price, themeId);
    }

    // ===== Constructor 1: crear nueva sala (sin id, será generado por la base de datos) =====
   /* public Room(String name, Difficulty difficulty, BigDecimal price, int themeId) {
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.themeId = themeId;
    }

    // ===== Constructor 2: cargar sala desde la base de datos (con id) =====
    public Room(Id<Room>  roomId, String name, Difficulty difficulty, BigDecimal price, int themeId) {
        this.roomId = roomId;
        this.name = name;
        this.difficulty = difficulty;
        // Si el precio es null, asignar valor por defecto 0
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.themeId = themeId;
    }*/


    public Id<Room>  getRoomId() {
        return roomId;
    }

    protected void setRoomId(Id<Room> roomId) {
        this.roomId = roomId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Id<Theme> getThemeId() {
        return themeId;
    }

    public void setThemeId(Id<Theme> themeId) {
        this.themeId = themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId
                , room.roomId
        );
    }

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
