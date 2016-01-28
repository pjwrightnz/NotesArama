package com.example.pjw527.notesarama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        notes.add(data.getStringExtra(MainActivity.noteContent));
        notesListViewAdaptor.notifyDataSetChanged();
    }
}
