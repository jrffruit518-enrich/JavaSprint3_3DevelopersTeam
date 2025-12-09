package room;
import common.dao.CrudDao;
import common.valueobject.*;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements CrudDao {



    @Override
    public void save(Object entity) {

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
