package com.example.marielazviskova.simplenotepadapp;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Mariela Zviskova on 30.7.2017 г..
 */

public class FileManager {

    public static void saveToInternalStorage(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("filename", Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(NoteAdapter.notes);
            of.flush();
            of.close();
            fos.close();
        } catch (Exception e) {
            Log.e("InternalStorage", e.getMessage());
        }
    }

    public static ArrayList<Note> readFromInternalStorage(Context context) {
        ArrayList<Note> toReturn = NoteAdapter.notes;
        FileInputStream fis;
        try {
            fis = context.openFileInput("filename");
            ObjectInputStream oi = new ObjectInputStream(fis);
            try {
                toReturn = (ArrayList<Note>) oi.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            oi.close();
        } catch (FileNotFoundException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (IOException e) {
            Log.e("InternalStorage", e.getMessage());
        }
        return toReturn;
    }
}
