package com.example.sharifahmed.simpletodoapp.DatabaseOperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sharifahmed.simpletodoapp.DatabaseHelper.TaskDatabaseHelper;
import com.example.sharifahmed.simpletodoapp.Model.TaskInfo;

import java.util.ArrayList;

/**
 * Created by SHARIF AHMED on 25-Jul-17.
 */
public class TaskDatabaseOperation {


    private TaskDatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private TaskInfo taskInfo;


    public TaskDatabaseOperation(Context context) {
        databaseHelper=new TaskDatabaseHelper(context);
    }


    public void open(){

        database=databaseHelper.getWritableDatabase();

    }

    public void close(){

        databaseHelper.close();

    }




    public boolean addTask(TaskInfo taskInfo){

        this.open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TaskDatabaseHelper.COL_TASK_NAME,taskInfo.getName());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DESC,taskInfo.getDescription());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DATE_CREATED,taskInfo.getDateCreated());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DATE_UPDATED,taskInfo.getDateUpdated());


        long inserted=database.insert(TaskDatabaseHelper.TABLE_TASK_INFO,null,contentValues);

        this.close();

        if(inserted>0){
            return true;
        }else {
            return false;
        }

    }




    public TaskInfo getTaskById(int taskId){

        this.open();

        Cursor cursor = database.query
                (
                        TaskDatabaseHelper.TABLE_TASK_INFO,

                        new String[]{TaskDatabaseHelper.COL_TASK_NAME,
                                TaskDatabaseHelper.COL_TASK_DESC,
                                TaskDatabaseHelper.COL_TASK_DATE_CREATED,
                                TaskDatabaseHelper.COL_TASK_DATE_UPDATED},

                        TaskDatabaseHelper.COL_TASK_ID + " = " +taskId,//Where Condition
                        null,
                        null,
                        null,
                        null
                );
        cursor.moveToFirst();


        String taskName = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_NAME));
        String taskDesc = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DESC));
        String taskDateCreated = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DATE_CREATED));
        String taskDateUpdated = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DATE_UPDATED));



        taskInfo = new TaskInfo(taskName,taskDesc,taskDateCreated,taskDateUpdated);

        this.close();

        return taskInfo;

    }



    public ArrayList<TaskInfo> getAllTask(){

        this.open();
        ArrayList<TaskInfo> taskList = new ArrayList<>();


        Cursor cursor = database.query
                (
                        TaskDatabaseHelper.TABLE_TASK_INFO,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );

        if(cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for(int i=0;i<cursor.getCount();i++){

                int mId = cursor.getInt(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_ID));
                String taskName = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_NAME));
                String taskDesc = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DESC));
                String taskDateCreated = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DATE_CREATED));
                String taskDateUpdated = cursor.getString(cursor.getColumnIndex(TaskDatabaseHelper.COL_TASK_DATE_UPDATED));


                taskInfo = new TaskInfo(mId,taskName,taskDesc,taskDateCreated,taskDateUpdated);
                taskList.add(taskInfo);

                cursor.moveToNext();

            }
        }

        cursor.close();
        this.close();
        return taskList;
    }


    public boolean deleteTask(int taskId){

        this.open();

        int deleted = database.delete
                (
                        TaskDatabaseHelper.TABLE_TASK_INFO,
                        TaskDatabaseHelper.COL_TASK_ID + " = " +taskId,
                        null
                );

        this.close();

        if (deleted>0){
            return true;
        }
        else {
            return false;
        }


    }


    public boolean updateTaskInfoById(int taskId,TaskInfo taskInfo){

        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(TaskDatabaseHelper.COL_TASK_NAME,taskInfo.getName());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DESC,taskInfo.getDescription());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DATE_CREATED,taskInfo.getDateCreated());
        contentValues.put(TaskDatabaseHelper.COL_TASK_DATE_UPDATED,taskInfo.getDateUpdated());

        int updated = database.update
                (
                        TaskDatabaseHelper.TABLE_TASK_INFO,
                        contentValues,
                        TaskDatabaseHelper.COL_TASK_ID + " = " + taskId,
                        null
                );

        this.close();

        if (updated>0){
            return true;
        }
        else {
            return false;
        }

    }


}