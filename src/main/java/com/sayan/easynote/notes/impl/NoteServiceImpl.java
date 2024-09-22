package com.sayan.easynote.notes.impl;

import com.sayan.easynote.notes.Note;
import com.sayan.easynote.notes.NoteRepository;
import com.sayan.easynote.notes.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public boolean updateNote(Note updatedNote, Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if(noteOptional.isPresent()) {
            Note note = noteOptional.get();
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            noteRepository.save(note);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteNote(Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if(noteOptional.isPresent()) {
            noteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
