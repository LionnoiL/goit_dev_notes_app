package ua.gaponov.todoApp.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {

  private long id;
  private String title;
  private String content;
}
