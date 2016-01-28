package com.example.pjw527.notesarama;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<String> notes;
    protected ListView notesListView;
    protected ArrayAdapter<String> notesListViewAdaptor;
    public static String noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.ic_home_black_24dp);
        menu.setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);
        notesListView = (ListView) findViewById(R.id.notesListView);
        notes = new ArrayList<String>();
        notes.add("note one");
        notes.add("note two");

        notesListViewAdaptor = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, notes
        );

        notesListView.setAdapter(notesListViewAdaptor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Info) {
            return true;
        } else if (id == R.id.action_NewNote) {

            Intent newNoteIntent = new Intent(MainActivity.this, NewNoteActivity.class);
            startActivityForResult(newNoteIntent, 0);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        notes.add(data.getStringExtra(MainActivity.noteContent));
        notesListViewAdaptor.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

        String text = notes.get(position);
        Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
        intent.putExtra(MainActivity.noteContent, text);
        startActivity(intent);

    }

}
