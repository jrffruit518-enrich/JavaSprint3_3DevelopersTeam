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
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public boolean delete(Id id) {
        return false;
    }
}
