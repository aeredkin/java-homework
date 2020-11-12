package org.homework.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotebookController {
    @Autowired
    private NotebookRepository repository;

    @RequestMapping("/notes")
    public List<Note> getNotes() {
        return repository.findAll();
    }

    @RequestMapping("/notes/{name}")
    public List<Note> getNotes(@PathVariable String name) {
        return repository.findByName(name);
    }

    @RequestMapping("/addnote")
    public Note addNote(@RequestParam(value="name") String name, @RequestParam(value="text") String text) {
        Note note = new Note();
        note.setName(name);
        note.setText(text);
        return repository.save(note);
    }
}
