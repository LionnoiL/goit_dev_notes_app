package ua.gaponov.todoApp.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.gaponov.todoApp.dto.UserDto;
import ua.gaponov.todoApp.entities.User;
import ua.gaponov.todoApp.entities.User.UserRoles;
import ua.gaponov.todoApp.repositories.UserRepository;
import ua.gaponov.todoApp.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;

  @GetMapping("/users/list")
  public ModelAndView getUserList() {
    ModelAndView result = new ModelAndView("users/list");
    List<UserDto> users = userService.listAll()
        .stream()
        .map(u -> u.toUserDto())
        .collect(Collectors.toList());
    result.addObject("users", users);
    return result;
  }

  @GetMapping("/users/add")
  public ModelAndView getUserAdd() {
    ModelAndView result = new ModelAndView("users/user");
    UserDto userDto = UserDto.builder().build();
    result.addObject("user", userDto);
    return result;
  }

  @PostMapping("/users/edit")
  public RedirectView postUserEdit(@RequestParam(value = "userName") String userName,
      @RequestParam(value = "userRole") String userRole,
      @RequestParam(value = "password") String password,
      @RequestParam(value = "enabled", required = false) String enabled,
      @RequestParam(value = "id") long id) {

    User user = null;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    if (id == 0) {
      user = new User();
      user.setPassword(hashedPassword);
      user.setUserName(userName);
      user.setEnabled(enabled != null ? 1 : 0);
      user.setRole("Admin".equals(userRole) ? UserRoles.ROLE_ADMIN : UserRoles.ROLE_USER);
      userService.add(user);
    } else {

      user = userService.getById(id);
      if (!password.isBlank()) {
        user.setPassword(hashedPassword);
      }
      user.setUserName(userName);
      user.setEnabled(enabled != null ? 1 : 0);
      user.setRole("Admin".equals(userRole) ? UserRoles.ROLE_ADMIN : UserRoles.ROLE_USER);
      userService.update(user);
    }
    return new RedirectView("/users/list");
  }

  @GetMapping("/users/edit")
  public ModelAndView getUserEdit(@RequestParam(value = "id") long id) {
    ModelAndView result = new ModelAndView("users/user");
    User user = userService.getById(id);
    String userRole = user.getRole().getFrendlyName();
    result.addObject("user", user.toUserDto());
    result.addObject("userRole", userRole);
    return result;
  }

  @PostMapping("/users/delete")
  public RedirectView postUserDelete(@RequestParam(value = "id") long id) {
    userService.deleteById(id);
    return new RedirectView("/users/list");
  }
}
