package com.example.sharifahmed.simpletodoapp.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHARIF AHMED on 25-Jul-17.
 */
public class TaskDatabaseHelper extends SQLiteOpenHelper {



    static final String DATABASE_NAME = "task_info_db";//HERE IS THE DATABASE

    public static final String TABLE_TASK_INFO            = "task_info_table";//HERE IS THE 'candidate_info_db' DATABASE TABLE NAME


    public static final String COL_TASK_ID                     = "task_id";

    public static final String COL_TASK_NAME                   = "task_name";

    public static final String COL_TASK_DESC                   = "task_desc";

    public static final String COL_TASK_DATE_CREATED           = "task_date_created";

    public static final String COL_TASK_DATE_UPDATED           = "task_date_updated";




    static final int DATABASE_VERSION = 1;


    String CREATE_TABLE_TASK_INFO = " CREATE TABLE " + TABLE_TASK_INFO +
            " ( " + COL_TASK_ID + " INTEGER PRIMARY KEY," +
            COL_TASK_NAME + " TEXT," +
            COL_TASK_DESC + " TEXT," +
            COL_TASK_DATE_CREATED + " TEXT," +
            COL_TASK_DATE_UPDATED + " TEXT )";



    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TASK_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
