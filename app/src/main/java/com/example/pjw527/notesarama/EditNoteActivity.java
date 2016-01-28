package com.example.pjw527.notesarama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

}
