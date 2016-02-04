package com.example.pjw527.notesarama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by pjw527 on 04/02/2016.
 */
public class NoteDbOpenHelper extends SQLiteOpenHelper{
    String tree;
    protected static final int version = 1;
    protected static final String databaseName = "notes.db";
    protected String CREATE_SQL = "create table Note (_id INTEGER PRIMARY KEY, noteTitle TEXT, noteDescription TEXT, importance INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
        createNote(db, "TestNote Title", "Test Note Desc", 3 );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public NoteDbOpenHelper(Context context) {
        super(context, databaseName, null, version);
    }

    public List<Note> getAllNotes() {
        String sql = "select * from Note";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{});
        int noteIndex = cursor.getColumnIndex("noteTitle");
        int noteDesIndex = cursor.getColumnIndex("noteDescription");
        int noteImpIndex = cursor.getColumnIndex("importance");
        for (int i = 0; i< cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Note newNote = new Note(cursor.getString(noteIndex), cursor.getString(noteDesIndex), cursor.getInt(noteImpIndex));
            Log.d("NoteDB", newNote.toString());
        }
        return null;
    }

    public void createNote(SQLiteDatabase db,String noteTitle, String noteDesc, int imp) {

        ContentValues values = new ContentValues();
        values.put("noteTitle", noteTitle);
        values.put("noteDescription", noteDesc);
        values.put("importance", imp);

        db.insert("Note", "null", values); //returns int ID of the not in the database. Allocate this to the note so you can track notes between DB and the application/list
    }

  //  public void updateNotes(SQLiteDatabase db, int noteId,   )




}
