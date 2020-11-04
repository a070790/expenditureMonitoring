package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Integer> {
    List<Check> findBySum(Object sum);
    List<Check> findByDate(Object date);
}
