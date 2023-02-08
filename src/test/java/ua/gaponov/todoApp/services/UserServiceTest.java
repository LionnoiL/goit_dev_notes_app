package ua.gaponov.todoApp.services;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ua.gaponov.todoApp.TestData;
import ua.gaponov.todoApp.entities.User;
import ua.gaponov.todoApp.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodName.class)
@TestPropertySource("classpath:test.properties")
class UserServiceTest {

  UserService userService;
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void beforeEach() {
    userService = new UserService(userRepository);
  }

  @Test
  void a_listAll() {
    List<User> users = userService.listAll();
    Assertions.assertEquals(2, users.size());
  }

  @Test
  void b_add() {
    userService.add(TestData.USER1);
  }

  @Test
  void c_getById() {
    Assertions.assertEquals(userService.getById(TestData.USER1.getId()), TestData.USER1);
  }

  @Test
  void d_getByName() {
    Assertions.assertEquals(userService.getByName(TestData.USER1.getUserName()), TestData.USER1);
  }

  @Test
  void e_update() {
    userService.update(TestData.USER1UPDATED);

    User userByIdUpdated = userService.getById(TestData.USER1.getId());
    Assertions.assertEquals(TestData.USER1UPDATED, userByIdUpdated);
  }

  @Test
  void f_deleteById() {
    userService.deleteById(TestData.USER1.getId());
    List<User> users = userService.listAll();
    Assertions.assertEquals(2, users.size());

    User userById = userService.getById(TestData.USER1.getId());
    Assertions.assertEquals(null, userById);
  }
}