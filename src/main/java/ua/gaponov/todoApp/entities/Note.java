package ua.gaponov.todoApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Note implements Comparable{

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

  @Override
  public int compareTo(Object o) {
    if (o instanceof Note == false){
      return -1;
    }

    if (o == null){
      return -1;
    }

    Note note = (Note) o;
    if (note.getTitle().equals(getTitle()) && note.getContent().equals(getContent())){
      return 0;
    }
    return -1;
  }
}
