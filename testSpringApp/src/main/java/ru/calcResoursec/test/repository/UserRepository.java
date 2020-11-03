package ru.calcResoursec.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.calcResoursec.test.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
