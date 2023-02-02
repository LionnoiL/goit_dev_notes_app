package ua.gaponov.todoApp.services;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.gaponov.todoApp.entities.Note;
import ua.gaponov.todoApp.repositories.NoteRepository;

@SpringBootTest
class NoteServiceTest {

  NoteService noteService;
  @Autowired
  private NoteRepository noteRepository;

  @BeforeEach
  void beforeEach() {

    noteService = new NoteService(noteRepository);
    noteRepository.deleteAll();

    noteService.add(createTestNote(1));
    noteService.add(createTestNote(2));
    noteService.add(createTestNote(3));
  }

  Note createTestNote(int index) {
    Note result = Note.builder()
        .id(index)
        .title("Note " + index)
        .content("Content note " + index)
        .build();
    return result;
  }

  @Test
  void listAll() {
    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(3, notes.size());

    for (int i = 1; i <= 3; i++) {
      Note noteById = noteService.getById(i);
      Note note = Note.builder().id(i).title("Note " + i).content("Content note " + i).build();
      Assertions.assertEquals(noteById, note);
    }
  }

  @Test
  void add() {
    Note note = createTestNote(4);
    noteService.add(note);

    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(4, notes.size());

    Note noteById = noteService.getById(4);

    Assertions.assertEquals(noteById, note);
  }

  @Test
  void deleteById() {
    noteService.deleteById(1);

    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(2, notes.size());

    Note noteById = noteService.getById(1);
    Assertions.assertEquals(null, noteById);
  }

  @Test
  void update() {
    Note noteById = noteService.getById(1);
    noteById.setTitle("Note 1 updated");
    noteById.setContent("Content note 1 updated");
    noteService.update(noteById);

    Note noteByIdUpdated = noteService.getById(1);
    Assertions.assertEquals(noteById, noteByIdUpdated);
  }

  @Test
  void getById() {
    Note noteById = noteService.getById(1);

    Note note = Note.builder().id(1).title("Note 1").content("Content note 1").build();

    Assertions.assertEquals(noteById, note);
  }
}