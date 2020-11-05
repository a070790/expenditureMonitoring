package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

<<<<<<< HEAD
import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Integer> {
    Check findOneByCheckNum(Object checkNum);
    List<Check> findByCheckNum(Object checkNum);
    List<Check> findByDate(Object date);
=======
public interface CheckRepository extends CrudRepository<Check, Long> {
>>>>>>> 085dfb237fa2e800d5bdf056f0b15e981fef6519
}
