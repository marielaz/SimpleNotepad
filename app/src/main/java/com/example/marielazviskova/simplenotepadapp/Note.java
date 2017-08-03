package com.example.marielazviskova.simplenotepadapp;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Mariela Zviskova on 30.7.2017 г..
 */

public class Note implements Serializable {

    private String title;
    private String content;
    private long dateTime;

    public Note(String title, String content, long dateTime) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
        if (content != null && !content.isEmpty()) {
            this.content = content;
        }
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String formattedDateTime(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
                , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(dateTime));
    }

}
