package com.example.pjw527.notesarama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    Button notedButton;
    EditText newNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        notedButton = (Button) findViewById(R.id.notedButton);
        newNoteText = (EditText) findViewById(R.id.editNoteText);

        notedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToMainIntent = new Intent(NewNoteActivity.this, MainActivity.class);
                returnToMainIntent.putExtra(MainActivity.noteContent, newNoteText.getText().toString());
                setResult(1, returnToMainIntent);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_done) {
            Intent returnToMainIntent = new Intent();
            returnToMainIntent.putExtra(MainActivity.noteContent, newNoteText.getText().toString());
            setResult(1, returnToMainIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
