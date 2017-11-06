package com.blazeworks.quicknote;

import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;


/**
 * Created by GuneetAtwal on 11/6/2017.
 */

public class Note {
    private String title ,body;
    private long noteId , dateCreateMilli;
    private Category category;
    public enum Category { PERSONAL , FINANCE , TECHNICAL , QUOTE }

    public Note(String title ,  String body , Category category){
        this.title = title;
        this.body = body;
        this.category = category;
        noteId = 0;
        dateCreateMilli = 0;
    }

    public Note(String title ,  String body , Category category , long noteId , long dateCreateMilli){
        this.title = title;
        this.body = body;
        this.category = category;
        this.noteId = noteId;
        this.dateCreateMilli = dateCreateMilli;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getNoteId() {
        return noteId;
    }

    public long getDateCreateMilli() {
        return dateCreateMilli;
    }

    public Category getCategory() {
        return category;
    }

    public TextDrawable getNoteIcon() {
        return categoryToDrawable(category);
    }

    /*
     * returns the icon based on the category of the note
     * By default the icon is set to Personal
     */
    public static TextDrawable categoryToDrawable(Category category) {
        String color;

        switch (category) {
            case PERSONAL:
                color = "#F44336";
                return TextDrawable.builder().buildRound("P", Color.parseColor(color));
            case TECHNICAL:
                color = "#3F51B5";
                return TextDrawable.builder().buildRound("T", Color.parseColor(color));
            case QUOTE:
                color = "#009688";
                return TextDrawable.builder().buildRound("Q", Color.parseColor(color));
            case FINANCE:
                color = "#607D8B";
                return TextDrawable.builder().buildRound("F", Color.parseColor(color));

        }

        return TextDrawable.builder().buildRound("P", Color.parseColor("#F44336"));
    }


}