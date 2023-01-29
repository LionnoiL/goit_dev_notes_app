package ua.gaponov.todoApp.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.gaponov.todoApp.entities.Note;

class NoteServiceTest {

  NoteService noteService;

  @BeforeEach
  void beforeEach(){
    noteService = new NoteService();
  }

  @Test
  void listAll() {
    Note note = new Note();
    note.setId(1);
    note.setTitle("Note 1");
    note.setContent("Content note 1");
    noteService.add(note);
    System.out.println("noteService.listAll() = " + noteService.listAll());
  }

  @Test
  void add() {
    Note note = new Note();
    note.setId(1);
    note.setTitle("Note 1");
    note.setContent("Content note 1");
    noteService.add(note);
    System.out.println("noteService.listAll() = " + noteService.listAll());
  }

  @Test
  void deleteById() {
    Note note = new Note();
    note.setId(1);
    note.setTitle("Note 1");
    note.setContent("Content note 1");
    noteService.add(note);

    Note note2 = new Note();
    note2.setId(2);
    note2.setTitle("Note 2");
    note2.setContent("Content note 2");
    noteService.add(note2);

    noteService.deleteById(1);

    System.out.println("noteService.listAll() = " + noteService.listAll());
  }

  @Test
  void update() {
    Note note = new Note();
    note.setId(1);
    note.setTitle("Note 1");
    note.setContent("Content note 1");
    noteService.add(note);

    note.setTitle("Note 1 updated");
    noteService.update(note);

    System.out.println("noteService.listAll() = " + noteService.listAll());
  }

  @Test
  void getById() {
    Note note = new Note();
    note.setId(1);
    note.setTitle("Note 1");
    note.setContent("Content note 1");
    noteService.add(note);

    Note note2 = new Note();
    note2.setId(2);
    note2.setTitle("Note 2");
    note2.setContent("Content note 2");
    noteService.add(note2);

    Note noteById = noteService.getById(2);
    System.out.println("noteById = " + noteById);
  }
}