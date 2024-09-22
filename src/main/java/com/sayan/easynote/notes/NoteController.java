package com.sayan.easynote.notes;


import com.sayan.easynote.notes.impl.NoteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note newNote = noteService.addNote(note);
        return new ResponseEntity<>(newNote, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getNotes(), HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long noteId){
        Note note = noteService.getNoteById(noteId);
        if(note != null){
            return new ResponseEntity<>(note, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable Long noteId, @RequestBody Note note){
        if(noteService.updateNote(note, noteId)){
            return new ResponseEntity<>("Note Updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Note Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id){
        if(noteService.deleteNote(id)){
            return new ResponseEntity<>("Note Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Note Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
