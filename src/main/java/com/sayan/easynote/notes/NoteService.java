package com.sayan.easynote.notes;

import java.util.List;

public interface NoteService {

    public Note addNote(Note note);
    public List<Note> getNotes();
    public Note getNoteById(Long id);
    public boolean updateNote(Note note, Long id);
    public boolean deleteNote(Long id);
}
