package com.example.marielazviskova.simplenotepadapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    private EditText titleEdit, contentEdit;
    private Button save;
    private Note note = new Note("", "", 0);
    private Toolbar toolbar;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleEdit = (EditText) findViewById(R.id.titleEdit);
        contentEdit = (EditText) findViewById(R.id.contentEdit);
        save = (Button) findViewById(R.id.edit);

        if (getIntent().getSerializableExtra("note") != null) {
            note = (Note) getIntent().getSerializableExtra("note");
            titleEdit.setText(note.getTitle());
            contentEdit.setText(note.getContent());
            position = getIntent().getExtras().getInt("position");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (note != null) {
                    String title = titleEdit.getText().toString();
                    String content = contentEdit.getText().toString();
                    note.setTitle(title);
                    note.setContent(content);
                    note.setDateTime(System.currentTimeMillis());

                    NoteAdapter.notes.remove(position);
                    NoteAdapter.notes.add(position, note);
                    FileManager.saveToInternalStorage(EditNoteActivity.this);
                    Toast.makeText(EditNoteActivity.this, "note has been saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
