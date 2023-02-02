package ua.gaponov.todoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.gaponov.todoApp.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
