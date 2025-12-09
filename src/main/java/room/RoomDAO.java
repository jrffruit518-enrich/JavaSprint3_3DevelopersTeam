package room;
import DataBaseConnection.MySQL_Data_Base_Connection;
import common.dao.CrudDao;
import common.valueobject.*;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements CrudDao {

private MySQL_Data_Base_Connection connection;

    public RoomDAO() {
        connection = MySQL_Data_Base_Connection.getInstance();
    }

    @Override
    public void save(Object entity) {
        String sql = "insert into "


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
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public boolean delete(Id id) {
        return false;
    }
}
