package com.example.pjw527.notesarama;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pjw527 on 04/02/2016.
 */
public class Note implements Serializable {

    String note;
    String noteDesc;
    int importance;

    public Note(String note, String noteDesc, int importance) {
        this.note = note;
        this.noteDesc = noteDesc;
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }




    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                ", noteDesc='" + noteDesc + '\'' +
                ", importance=" + importance +
                '}';
    }
}
