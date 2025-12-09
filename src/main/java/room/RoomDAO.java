package room;
import DataBaseConnection.MySQL_Data_Base_Connection;
import common.dao.CrudDao;
import common.valueobject.*;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements CrudDao <Room>{

private final Connection connection;

    public RoomDAO() {
        connection = MySQL_Data_Base_Connection.getInstance().getConnection();
    }


    @Override
    public void save(Room room) {
        String sql = "insert into room(name,difficulty,price,theme_id)values(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,room.getName());
            ps.setString(2,room.getDifficulty().toString());
            ps.setBigDecimal(3,room.getPrice());
            ps.setInt(4,room.getThemeId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Database error: Room creation failed, no rows affected.");
            }
            //El siguiente código asigna directamente el ID generado por MySQL al objeto para facilitar las consultas por ID.
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Integer newId = generatedKeys.getInt(1);
                    room.setRoomId(newId);
                } else {
                    throw new RuntimeException("Room saved but failed to retrieve generated ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error saving Room: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional findById(Id id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean update(Room room) {
        String sql = "UPDATE room SET name = ?, difficulty = ?, price = ?, theme_id = ? WHERE id_room = ?";
        if (room.getRoomId() == null) {
            throw new IllegalArgumentException("Cannot update Room: ID is missing.");
        }
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, room.getName());
            ps.setString(2, room.getDifficulty().toString());
            ps.setBigDecimal(3, room.getPrice());
            ps.setInt(4, room.getThemeId());
            ps.setInt(5, room.getRoomId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error updating Room: " + room.getName(), e);
        }
    }

    @Override
    public boolean delete(Id id) {
        int roomId = id.value();
        String sql = "DELETE FROM room WHERE id_room = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error deleting Room with ID: " + roomId, e);
        }
    }
}
