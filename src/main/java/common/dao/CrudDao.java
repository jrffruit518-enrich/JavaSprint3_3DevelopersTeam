package common.dao;

import common.valueobject.Id;
import common.valueobject.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    void save(T entity);

    Optional<T> findById(Id<T> id);

    List<T> findAll();

    boolean update(T entity);

    boolean delete(Id<T> id);


}

