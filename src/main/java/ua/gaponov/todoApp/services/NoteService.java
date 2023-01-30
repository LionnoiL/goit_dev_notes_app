package ua.gaponov.todoApp.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ua.gaponov.todoApp.entities.Note;

@Service
public class NoteService {

  private List<Note> items = new ArrayList<>();
  private long index;

  public List<Note> listAll() {
    return items;
  }

  public Note add(Note note) {
    note.setId(++index);
    items.add(note);
    return note;
  }

  public void deleteById(long id) {
    Note note = getById(id);
    items.remove(items.indexOf(note));
  }

  public void update(Note note) {
    items.set(items.indexOf(note), note);
  }

  public Note getById(long id) {
    return items.stream()
        .filter((note) -> note.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
