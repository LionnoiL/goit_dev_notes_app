package ua.gaponov.todoApp.dto;

import lombok.Builder;
import lombok.Data;
import ua.gaponov.todoApp.entities.User.UserRoles;

@Data
@Builder
public class UserDto {
  private long id;
  private String userName;
  private UserRoles role;
  private int enabled;
}
