package com.example.pjw527.notesarama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    EditText editNoteText;
    Button editNotedButton;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_note);



        editNoteText = (EditText) findViewById(R.id.editNoteText);
        editNotedButton = (Button) findViewById(R.id.editNotedButton);
        editNoteText.setText(getIntent().getStringExtra(MainActivity.noteContent));
        editNoteText.setSelection(editNoteText.getText().length());
        position = getIntent().getIntExtra(MainActivity.position, -1);

        editNotedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToMainIntent = new Intent(EditNoteActivity.this, MainActivity.class);
                returnToMainIntent.putExtra(MainActivity.noteContent, editNoteText.getText().toString());
                returnToMainIntent.putExtra(MainActivity.position, position);
                setResult(1, returnToMainIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
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
            Intent returnToMainIntent = new Intent(EditNoteActivity.this, MainActivity.class);
            returnToMainIntent.putExtra(MainActivity.noteContent, editNoteText.getText().toString());
            returnToMainIntent.putExtra(MainActivity.position, position);
            setResult(1, returnToMainIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
