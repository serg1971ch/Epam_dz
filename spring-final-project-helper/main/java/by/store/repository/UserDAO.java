package by.store.repository;

import by.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, String> {

    User findByUsername(String username);

}
