package ru.calcResoursec.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.calcResoursec.test.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findAllByCheck_Id(Object check_id);
    Purchase findOneByName(Object name);
//    void deleteOneById(Object id);
}
