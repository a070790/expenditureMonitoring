package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Integer> {
    Check findOneByCheckNum(Object checkNum);
    List<Check> findByCheckNum(Object checkNum);
    List<Check> findByDate(Object date);
}
