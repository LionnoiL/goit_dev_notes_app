package ua.gaponov.todoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.gaponov.todoApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.username = :name")
  User findByName(String name);

}
