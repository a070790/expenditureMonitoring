package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
