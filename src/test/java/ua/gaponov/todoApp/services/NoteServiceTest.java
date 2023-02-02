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
    Note result = new Note();
    result.setId(index);
    result.setTitle("Note " + index);
    result.setContent("Content note " + index);
    return result;
  }

  @Test
  void listAll() {
    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(3, notes.size());

    for (int i = 1; i <= 3; i++) {
      Note noteById = noteService.getById(i);
      Assertions.assertEquals("Note " + i, noteById.getTitle());
      Assertions.assertEquals("Content note " + i, noteById.getContent());
      Assertions.assertEquals(i, noteById.getId());
    }
  }

  @Test
  void add() {
    Note note = createTestNote(4);
    noteService.add(note);

    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(4, notes.size());

    Note noteById = noteService.getById(4);
    Assertions.assertEquals(4, noteById.getId());
    Assertions.assertEquals("Note 4", noteById.getTitle());
    Assertions.assertEquals("Content note 4", noteById.getContent());
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
    Assertions.assertEquals("Note 1 updated", noteByIdUpdated.getTitle());
    Assertions.assertEquals("Content note 1 updated", noteByIdUpdated.getContent());
  }

  @Test
  void getById() {
    Note noteById = noteService.getById(1);
    Assertions.assertEquals("Note 1", noteById.getTitle());
    Assertions.assertEquals("Content note 1", noteById.getContent());
    Assertions.assertEquals(1, noteById.getId());
  }
}