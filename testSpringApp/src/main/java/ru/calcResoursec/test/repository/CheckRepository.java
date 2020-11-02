package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Check;

public interface CheckRepository extends CrudRepository<Check, Long> {
}
