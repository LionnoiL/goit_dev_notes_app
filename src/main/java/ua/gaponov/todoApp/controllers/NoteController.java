package ua.gaponov.todoApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.gaponov.todoApp.entities.Note;
import ua.gaponov.todoApp.services.NoteService;

@Controller
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;

  @GetMapping("/note/list")
  public ModelAndView getNoteList() {
    ModelAndView result = new ModelAndView("note/list");
    result.addObject("notes", noteService.listAll());
    return result;
  }

  @GetMapping("/note/add")
  public ModelAndView getNoteAdd() {
    ModelAndView result = new ModelAndView("note/note");
    Note note = new Note();
    result.addObject("note", note);
    return result;
  }

  @PostMapping("/note/edit")
  public RedirectView postNoteEdit(@RequestParam(value = "noteTitle") String noteTitle,
      @RequestParam(value = "noteContent") String noteContent,
      @RequestParam(value = "id") long id) {
    Note note = null;
    if (id == 0) {
      note = new Note(noteTitle, noteContent);
      noteService.add(note);
    } else {
      note = noteService.getById(id);
      note.setTitle(noteTitle);
      note.setContent(noteContent);
      noteService.update(note);
    }
    return new RedirectView("/note/list");
  }

  @GetMapping("/note/edit")
  public ModelAndView getNoteEdit(@RequestParam(value = "id") long id) {
    ModelAndView result = new ModelAndView("note/note");
    Note note = noteService.getById(id);
    result.addObject("note", note);
    return result;
  }

  @PostMapping("/note/delete")
  public RedirectView postNoteDelete(@RequestParam(value = "id") long id) {
    noteService.deleteById(id);
    return new RedirectView("/note/list");
  }
}
