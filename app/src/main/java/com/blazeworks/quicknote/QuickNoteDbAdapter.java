package com.blazeworks.quicknote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by GuneetAtwal on 11/9/2017.
 */

public class QuickNoteDbAdapter {

    private static final String DATABSE_NAME = "quicknote.db";
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
}
