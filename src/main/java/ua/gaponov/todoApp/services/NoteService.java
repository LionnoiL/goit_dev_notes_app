package ua.gaponov.todoApp.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gaponov.todoApp.entities.Note;
import ua.gaponov.todoApp.repositories.NoteRepository;

@RequiredArgsConstructor
@Service
public class NoteService {
  private final NoteRepository noteRepository;

  public List<Note> listAll() {
    return noteRepository.findAll();
  }

  public Note add(Note note) {
    noteRepository.save(note);
    return note;
  }

  public void deleteById(long id) {
    noteRepository.deleteById(id);
  }

  public void update(Note note) {
    noteRepository.save(note);
  }

  public Note getById(long id) {
    return noteRepository.findById(id).orElse(null);
  }
}
