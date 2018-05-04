package cs320stu10.homework2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidkrystall on 12/12/15.
 */
public class NoteBean {
    List<Note> notes;

    public NoteBean()
    {
        notes = new ArrayList<Note>();
    }
    public void setAddNote(String dummy){
        Note note = new Note();
        notes.add(note);
    }
    public List<Note> getNotes(){
        return notes;
    }
    public void setEntries(List<Note> notes){
        this.notes = notes;
    }
}
