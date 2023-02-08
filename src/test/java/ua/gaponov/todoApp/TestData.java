package ua.gaponov.todoApp;

import java.util.ArrayList;
import java.util.List;
import ua.gaponov.todoApp.entities.Note;
import ua.gaponov.todoApp.entities.User;
import ua.gaponov.todoApp.entities.User.UserRoles;

public class TestData {

  //Test Notes:
  public static Note NOTE1 = generateNote(1);
  public static Note NOTE2 = generateNote(2);
  public static Note NOTE3 = generateNote(3);
  public static Note NOTE4 = generateNote(4);
  public static Note NOTE1UPDATED = generateUpdatedNote(1);

  public static List<Note> NOTE_LIST = List.of(NOTE1, NOTE2, NOTE3);

  private static Note generateNote(int index) {
    Note result = Note.builder()
        .id(index)
        .title("Note " + index)
        .content("Content note " + index)
        .build();
    return result;
  }

  private static Note generateUpdatedNote(int index) {
    Note result = Note.builder()
        .id(index)
        .title("Note " + index + " updated")
        .content("Content note " + index + " updated")
        .build();
    return result;
  }

  //Test Users
  //Created Admin SQL
  public static final String TEST_ADMIN_NAME = "admin";
  public static final String TEST_ADMIN_PASSWORD = "$2a$12$GR09t6IovGn62hWk2jXlLeYQNHedn1tZwMUxrTirgmT48BS/C5cEa";
  public static final String TEST_ADMIN_ROLE = UserRoles.ROLE_ADMIN.toString();

  //Created USER SQL
  public static final String TEST_USER_NAME = "user";
  public static final String TEST_USER_PASSWORD = "$2a$12$TsY2xRUnGRcbm7EdYYW6.uGfPWC0KyrX9HCo9xWPmbDZFa51vdAfC";
  public static final String TEST_USER_ROLE = UserRoles.ROLE_USER.toString();

  public static List<User> TEST_USERS_LIST = new ArrayList<>();
  //New User
  public static User USER1 = generateUser();
  public static User USER1UPDATED = generateUpdatedUser();

  private static User generateUser(){
    User user = User.builder()
        .id(3)
        .userName("user1")
        .role(UserRoles.ROLE_USER)
        .password("$2a$12$LUYgkeKOsBCBMMUxYkM24.T86sfIMkbJiRoA.yCb9fehY6vV2XMVC")
        .build();
    return user;
  }

  private static User generateUpdatedUser(){
    User user = User.builder()
        .id(3)
        .userName("user1 updated")
        .role(UserRoles.ROLE_ADMIN)
        .password("$2a$12$tsApq9cIzXLG/4u5/14rTu18Bo.IavHkKbtWZ9ynz36crDNxYQafu") //000
        .build();
    return user;
  }

}
