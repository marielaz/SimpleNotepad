package com.example.marielazviskova.simplenotepadapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class AddNoteActivity extends AppCompatActivity {

    private EditText titleAdd;
    private EditText contentAdd;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleAdd = (EditText) findViewById(R.id.titleAddNote);
        contentAdd = (EditText) findViewById(R.id.contentAddNote);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save_note:
                validateAndSave();
                break;

            case R.id.action_cancel:
                actionCancel();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        actionCancel();
    }

    private void actionCancel() {

        if (titleAdd.getText().toString().isEmpty() && contentAdd.getText().toString().isEmpty()) {
            finish();
        } else {
            AlertDialog.Builder dialogCancel = new AlertDialog.Builder(this)
                    .setTitle("Discard changes")
                    .setMessage("Are you sure you do not want to save this note?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("NO", null);
            dialogCancel.show();
        }
    }


    private void validateAndSave() {

        String titleTxt = titleAdd.getText().toString();
        String contentTxt = contentAdd.getText().toString();

        if (titleTxt.isEmpty()) {
            Toast.makeText(AddNoteActivity.this, "please enter a title!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        if (contentTxt.isEmpty()) {
            Toast.makeText(AddNoteActivity.this, "please enter a content for your note!"
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        NoteAdapter.notes.add(new Note(titleTxt, contentTxt, System.currentTimeMillis()));
        FileManager.saveToInternalStorage(this);
        Toast.makeText(this, "note has been saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}

