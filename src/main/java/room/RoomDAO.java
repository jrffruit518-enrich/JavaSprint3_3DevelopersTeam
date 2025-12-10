package room;

import DataBaseConnection.MySQL_Data_Base_Connection;
import common.dao.CrudDao;
import common.valueobject.Id;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements CrudDao<Room> {

    private final Connection connection;

    public RoomDAO() {
        connection = MySQL_Data_Base_Connection.getInstance().getConnection();
    }


    @Override
    public void save(Room room) {
        String sql = "insert into room(name,difficulty,price,theme_id)values(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, room.getName());
            ps.setString(2, room.getDifficulty().toString());
            ps.setBigDecimal(3, room.getPrice());
            ps.setInt(4, room.getThemeId());
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
        final String SQL = "SELECT room_id, name, difficulty, price, theme_id FROM rooms WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {

            int roomId = id.value();
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Room room = new Room();

                    room.setRoomId(rs.getInt("room_id"));
                    room.setName(rs.getString("name"));
                    String difficultyString = rs.getString("difficulty");
                    room.setDifficulty(Difficulty.valueOf(difficultyString.toUpperCase()));
                    room.setPrice(rs.getBigDecimal("price"));
                    room.setThemeId(rs.getInt("theme_id"));

                    return Optional.of(room);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred while finding Room by ID: " + id);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List findAll() {
        final String SQL = "SELECT room_id, name, difficulty, price, theme_id FROM rooms";

        List<Room> rooms = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) { // 执行查询

            // 3. 遍历结果集
            while (rs.next()) {
                // 4. 将每一行记录映射成一个 Room 对象
                Room room = new Room();

                // 设置基本属性
                room.setRoomId(rs.getInt("room_id"));
                room.setName(rs.getString("name"));

                // 处理 Difficulty 枚举类型
                String difficultyString = rs.getString("difficulty");
                try {
                    // 假设 Difficulty 是枚举，且数据库存储的是其名称字符串
                    room.setDifficulty(Difficulty.valueOf(difficultyString.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    // 处理数据库中存储的 difficulty 值无效的情况
                    System.err.println("Warning: Invalid difficulty value in DB for room ID " + room.getRoomId() + ": " + difficultyString);
                    // 可以选择跳过此记录、设置默认值或抛出更上层的异常
                    continue;
                }

                // 设置价格和主题ID
                room.setPrice(rs.getBigDecimal("price"));
                room.setThemeId(rs.getInt("theme_id"));

                // 5. 将 Room 对象添加到列表中
                rooms.add(room);
            }

        } catch (SQLException e) {
            // 6. 异常处理：打印错误并返回空列表或抛出运行时异常
            System.err.println("Database error occurred while finding all Rooms: " + e.getMessage());
            e.printStackTrace();
            // 在实际应用中，通常会抛出 DataAccessException 或 RuntimeException
            return new ArrayList<>();
        }

        // 7. 返回填充好的列表
        return rooms;
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