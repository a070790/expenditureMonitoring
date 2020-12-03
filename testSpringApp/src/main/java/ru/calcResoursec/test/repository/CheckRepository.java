package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Integer> {
    Check findOneById(Object id);
    Check findOneByUserAndDate(Object user, Object date);
    List<Check> findAllByUser(Object user);
    List<Check> findAllByDate(Object date);
}
