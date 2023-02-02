package ua.gaponov.todoApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String content;

  public Note(long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Note() {
  }
}
