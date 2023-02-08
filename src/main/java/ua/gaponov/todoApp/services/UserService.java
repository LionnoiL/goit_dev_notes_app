package ua.gaponov.todoApp.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gaponov.todoApp.entities.User;
import ua.gaponov.todoApp.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public List<User> listAll() {
    return userRepository.findAll();
  }

  public User add(User user) {
    userRepository.save(user);
    return user;
  }

  public void deleteById(long id) {
    userRepository.deleteById(id);
  }

  public void update(User user) {
    userRepository.save(user);
  }

  public User getById(long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User getByName(String name){
    return userRepository.findByName(name);
  }
}
