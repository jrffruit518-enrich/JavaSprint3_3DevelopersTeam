package room;

import common.dao.CrudDao;
import common.valueobject.Id;
import common.valueobject.Price;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: RoomDAO
 * Package: room
 * Description:
 * Author: Rong Jiang
 * Create:10/12/2025 - 21:59
 * Version:v1.0
 *
 */
public interface RoomDAO extends CrudDao<Room> {
    @Override
    default void save(Room entity) {

    }

    @Override
    default Optional<Room> findById(Id<Room> id) {
        return Optional.empty();
    }

    @Override
    default List<Room> findAll() {
        return List.of();
    }

    @Override
    default boolean update(Room entity) {
        return false;
    }

    @Override
    default boolean delete(Id<Room> id) {
        return false;
    }

    default int count() {
        return 0;
    }

    default Price calculateTotalPrice() {
        return null;
    }
}
