package com.blazeworks.quicknote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by GuneetAtwal on 11/9/2017.
 */

public class QuickNoteDbAdapter {

    private static final String DATABASE_NAME = "quicknote.db";
    private static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "notes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    private final String[] allColumns = {COLUMN_ID , COLUMN_TITLE , COLUMN_BODY , COLUMN_CATEGORY , COLUMN_DATE};

    public static final String DATABASE_CREATE = "create table " + NOTE_TABLE + " ("
            + COLUMN_ID + " integer " + "PRIMARY KEY " + "AUTOINCREMENT "
            + COLUMN_TITLE + " text " + "NOT NULL "
            + COLUMN_BODY + " text " + "NOT NULL "
            + COLUMN_CATEGORY + " integer " + "NOT NULL "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private QuickNoteDbHelper quickNoteDbHelper;


    QuickNoteDbAdapter(Context context){
        this.context = context;
    }

    /*
     * This method is used to get our database or open our dattabase
     * so that we can manipulate it later.
     */

    public QuickNoteDbAdapter openDatabase() throws android.database.SQLException {

        quickNoteDbHelper = new QuickNoteDbHelper(context);
        sqLiteDatabase = quickNoteDbHelper.getWritableDatabase();

        return this;
    }

    public void closeDatabase(){
        quickNoteDbHelper.close();
    }

    public ArrayList<Note> getAllNotes (){
        ArrayList<Note> notes = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(NOTE_TABLE , allColumns , null , null , null , null , null);

        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Note note = cursorToNote(cursor);
            notes.add(note);
        }
        cursor.close();

        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        Note newNote = new Note(cursor.getString(1) , cursor.getString(2)
                , Note.Category.valueOf(cursor.getString(3)) , cursor.getLong(0) , cursor.getLong(4));

        return newNote;
    }

    private static class QuickNoteDbHelper extends SQLiteOpenHelper{

        QuickNoteDbHelper(Context context){
            super(context , DATABASE_NAME , null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /* Create a new table */
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(QuickNoteDbHelper.class.getName() ,"The databass has upgraded from" + oldVersion + " to " + newVersion + " which will destroy all data ");
            /* Destroys the table with drop command */
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);
        }
    }
}
