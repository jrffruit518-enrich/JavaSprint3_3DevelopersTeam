package decoracion;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: CrudDAO
 * Package: decoracion
 * Description:
 * Author: Rong Jiang
 * Create:09/12/2025 - 0:15
 * Version:v1.0
 *
 */
public interface CrudDAO <T> {
    void save(T entity);

    Optional<T> findById(Id<T> id);

    List<T> findAll();

    boolean update(T entity);

    boolean delete(Id<T> id);
}
