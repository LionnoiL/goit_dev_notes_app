package ua.gaponov.todoApp.services;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ua.gaponov.todoApp.TestData;
import ua.gaponov.todoApp.entities.Note;
import ua.gaponov.todoApp.repositories.NoteRepository;

@SpringBootTest
@TestMethodOrder(MethodName.class)
@TestPropertySource("classpath:test.properties")
class NoteServiceTest {

  NoteService noteService;
  @Autowired
  private NoteRepository noteRepository;

  @BeforeEach
  void beforeEach() {
    noteService = new NoteService(noteRepository);
  }

  @Test
  void a_add() {
    noteService.add(TestData.NOTE1);
    noteService.add(TestData.NOTE2);
    noteService.add(TestData.NOTE3);
    noteService.add(TestData.NOTE4);

    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(4, notes.size());

    Note noteById = noteService.getById(TestData.NOTE4.getId());
    Assertions.assertEquals(TestData.NOTE4, noteById);
  }

  @Test
  void b_deleteById() {
    noteService.deleteById(4);

    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(3, notes.size());

    Note noteById = noteService.getById(4);
    Assertions.assertEquals(null, noteById);
  }

  @Test
  void c_listAll() {
    List<Note> notes = noteService.listAll();
    Assertions.assertEquals(3, notes.size());
    Assertions.assertEquals(notes, TestData.NOTE_LIST);
  }

  @Test
  void d_getById() {
    Assertions.assertEquals(noteService.getById(TestData.NOTE1.getId()), TestData.NOTE1);
    Assertions.assertEquals(noteService.getById(TestData.NOTE2.getId()), TestData.NOTE2);
    Assertions.assertEquals(noteService.getById(TestData.NOTE3.getId()), TestData.NOTE3);
  }

  @Test
  void e_update() {
    noteService.update(TestData.NOTE1UPDATED);

    Note noteByIdUpdated = noteService.getById(1);
    Assertions.assertEquals(TestData.NOTE1UPDATED, noteByIdUpdated);
  }
}