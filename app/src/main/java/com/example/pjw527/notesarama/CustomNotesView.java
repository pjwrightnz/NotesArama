package com.example.pjw527.notesarama;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by pjw527 on 04/02/2016.
 */
public class CustomNotesView extends LinearLayout {

    protected TextView noteTitle, noteDesc;

    public CustomNotesView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(android.R.layout.simple_list_item_2, this, true);

        noteTitle = (TextView) findViewById(android.R.id.text1);
        noteDesc = (TextView) findViewById(android.R.id.text2);
    }
        public void setNote(Note note) {
            noteTitle.setText(note.getNote());
                    noteDesc.setText(note.getNoteDesc());
            }


}
