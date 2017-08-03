package com.example.marielazviskova.simplenotepadapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mariela Zviskova on 30.7.2017 г..
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    private Activity activity;
    public static ArrayList<Note> notes = new ArrayList<>();
    private View row;

    public NoteAdapter(Activity activity, ArrayList<Note> notes) {
        this.activity = activity;
        this.notes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        row = li.inflate(R.layout.single_row_note, parent, false);
        NoteAdapter.NoteViewHolder vh = new NoteAdapter.NoteViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder vh, final int position) {
        final Note note = notes.get(position);
        vh.dateTime.setText(note.formattedDateTime(activity));
        vh.title.setText(note.getTitle());
        vh.content.setText(note.getContent());

        vh.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, EditNoteActivity.class);
                i.putExtra("note", notes.get(position));
                i.putExtra("position", position);
                activity.startActivity(i);
                notifyItemChanged(position);
            }
        });

        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notes.get(position) != null) {
                    // FileManager.deleteFile(activity, note.getTitle());
                    notes.remove(notes.get(position));
                    notifyItemRangeChanged(position, notes.size());
                    notifyItemRemoved(position);
                    Toast.makeText(activity, "note is deleted"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout linearLayout;
        TextView dateTime;
        TextView content;
        TextView title;
        Button delete;

        NoteViewHolder(View row) {
            super(row);
            dateTime = (TextView) row.findViewById(R.id.single_note_date);
            content = (TextView) row.findViewById(R.id.single_note_content);
            title = (TextView) row.findViewById(R.id.single_note_title);
            linearLayout = (RelativeLayout) row.findViewById(R.id.single_row_note);
            delete = (Button) row.findViewById(R.id.single_note_delete);
        }
    }

}
