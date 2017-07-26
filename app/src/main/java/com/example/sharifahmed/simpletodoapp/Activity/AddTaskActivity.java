package com.example.sharifahmed.simpletodoapp.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sharifahmed.simpletodoapp.DatabaseHelper.TaskDatabaseHelper;
import com.example.sharifahmed.simpletodoapp.DatabaseOperation.TaskDatabaseOperation;
import com.example.sharifahmed.simpletodoapp.Model.TaskInfo;
import com.example.sharifahmed.simpletodoapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    ImageButton timerIB;
    EditText taskNameET;
    EditText taskDescET;
    EditText taskDateCreatedET;
    Button saveTaskBT;

    TaskDatabaseOperation taskDatabaseOperation;
    TaskDatabaseHelper taskDatabaseHelper;
    TaskInfo taskInfo;
    TaskInfo taskInfoById;


    int year_x;
    int month_x;
    int day_x;
    static final int DIALOG_ID = 0;

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskDatabaseOperation = new TaskDatabaseOperation(this);



        timerIB = (ImageButton) findViewById(R.id.timerImageButtonId);
        taskNameET = (EditText) findViewById(R.id.taskNameId);
        taskDescET = (EditText) findViewById(R.id.taskDescriptionId);
        taskDateCreatedET = (EditText) findViewById(R.id.taskDateCreatedId);
        saveTaskBT = (Button) findViewById(R.id.saveTaskId);



        //**************************** For Calender Current Date ***************************************************

        Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);




        //**************************** For Back Button ***************************************************

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //**************************** End Back Button Work **********************************************


        //Get TaskId from TaskDetailsActivity

        taskId = getIntent().getIntExtra("TaskId",0);

        if(taskId>0){
            try {
                taskInfoById = taskDatabaseOperation.getTaskById(taskId);
                taskNameET.setText(taskInfoById.getName());
                taskDescET.setText(taskInfoById.getDescription());
                taskDateCreatedET.setText(taskInfoById.getDateCreated());
                saveTaskBT.setText("Update");
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),"Something Wrong !!!!!!!!!",Toast.LENGTH_SHORT).show();
            }

        }


    }





    public void saveTask(View view) {



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String taskName = taskNameET.getText().toString().trim();
        String taskDesc = taskDescET.getText().toString().trim();
        String taskDateCreated = taskDateCreatedET.getText().toString().trim();
        String taskDateUpdate = df.format(c.getTime());



        if(!TextUtils.isEmpty(taskName) && !TextUtils.isEmpty(taskDesc) && !TextUtils.isEmpty(taskDateCreated)){

            if(taskId>0){
                if(taskName.equals(taskInfoById.getName()) && taskDesc.equals(taskInfoById.getDescription()) && taskDateCreated.equals(taskInfoById.getDateCreated())){
                    Toast.makeText(AddTaskActivity.this, "Task Not Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    taskInfo = new TaskInfo(taskName,taskDesc,taskDateCreated,taskDateUpdate);
                    taskDatabaseOperation.updateTaskInfoById(taskId,taskInfo);
                    Toast.makeText(AddTaskActivity.this, "Task Updated Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddTaskActivity.this, TaskDetailsActivity.class);
                    intent.putExtra("TaskId",taskId);
                    startActivity(intent);
                }
            }else{

                taskInfo = new TaskInfo(taskName,taskDesc,taskDateCreated,taskDateUpdate);
                taskDatabaseOperation.addTask(taskInfo);
                Toast.makeText(AddTaskActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(AddTaskActivity.this, ViewTaskActivity.class);
                startActivity(intent);
            }

        }
        else{
            Toast.makeText(AddTaskActivity.this, "Fiedl does not empty", Toast.LENGTH_SHORT).show();
        }

    }

    public void setTaskDate(View view) {
        showDialog(DIALOG_ID);
    }



    //**********************************For DatePicker dialog**********************************************
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this,datePickerListener,year_x,month_x,day_x);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return datePickerDialog;
        }
        else{
            return null;
        }

    }
    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            year_x = year;
            month_x = month + 1;
            day_x = day;

            taskDateCreatedET.setText(day_x+"-"+month_x+"-"+year_x);
        }
    };










    //**************************** For Menu Item Work ***************************************************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(AddTaskActivity.this);
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.actionViewId : //For Back Button
                Intent intent = new Intent(this,ViewTaskActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home : //For Back Button
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


//**********************************End Menu Item Work**********************************************








}
