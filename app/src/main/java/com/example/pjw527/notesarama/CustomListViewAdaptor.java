package com.example.pjw527.notesarama;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by pjw527 on 04/02/2016.
 */
public class CustomListViewAdaptor extends ArrayAdapter<Note> {

    public CustomListViewAdaptor(Context context, List<Note> objects) {

        super(context, android.R.layout.simple_list_item_2, objects);
            }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomNotesView view = new CustomNotesView(getContext());
        view.setNote(getItem(position));
        return view;
    }



}
