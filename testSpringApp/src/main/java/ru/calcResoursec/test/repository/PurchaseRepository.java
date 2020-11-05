package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
