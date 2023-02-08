package ua.gaponov.todoApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import ua.gaponov.todoApp.dto.UserDto;

@Data
@Entity
@Builder
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long id;
  @Column(name = "username", nullable = false, unique = true)
  private String userName;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRoles role;
  private int enabled;

  public UserDto toUserDto(){
    UserDto userDto = UserDto.builder()
        .id(getId())
        .role(getRole())
        .enabled(getEnabled())
        .userName(getUserName()).build();
    return userDto;
  }

  public User() {
  }

  public User(long id, String userName, String password, UserRoles role, int enabled) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
  }

  public enum UserRoles{
    ROLE_ADMIN ("Admin"),
    ROLE_USER ("User");

    private String frendlyName;

    UserRoles(String frendlyName) {
      this.frendlyName = frendlyName;
    }

    public String getFrendlyName() {
      return frendlyName;
    }
  }
}
