package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

public interface CheckRepository extends CrudRepository<Check, Integer> {
<<<<<<< Updated upstream
=======
    Check findOneById(Object id);
    Check findOneByUserAndDate(Object user, Object date);
    List<Check> findAllByUser(Object user);
    List<Check> findAllByDate(Object date);
    List<Check> findAll();
>>>>>>> Stashed changes
}
