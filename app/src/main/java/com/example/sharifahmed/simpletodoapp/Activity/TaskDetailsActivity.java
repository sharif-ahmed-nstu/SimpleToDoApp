package com.example.sharifahmed.simpletodoapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharifahmed.simpletodoapp.DatabaseOperation.TaskDatabaseOperation;
import com.example.sharifahmed.simpletodoapp.Model.TaskInfo;
import com.example.sharifahmed.simpletodoapp.R;

public class TaskDetailsActivity extends AppCompatActivity {


    TextView detailsTaskNameTV;
    TextView detailsTaskDescTV;
    TextView detailsTaskCreatedTV;
    TextView detailsTaskUpdatedTV;

    TaskDatabaseOperation taskDatabaseOperation;
    TaskInfo taskInfo;

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        taskDatabaseOperation = new TaskDatabaseOperation(this);
        taskInfo = new TaskInfo();

        detailsTaskNameTV = (TextView) findViewById(R.id.taskNameDetailsId);
        detailsTaskDescTV = (TextView) findViewById(R.id.taskDetailsDescriptionId);
        detailsTaskCreatedTV = (TextView) findViewById(R.id.taskDetailsDateCreatedId);
        detailsTaskUpdatedTV = (TextView) findViewById(R.id.taskDetailsUpdatedId);

        taskId = getIntent().getIntExtra("TaskId",0);
        if(taskId!=0){
            taskInfo = taskDatabaseOperation.getTaskById(taskId);
        }
        /*
        String taskName = getIntent().getStringExtra("TaskName");
        String taskDesc = getIntent().getStringExtra("TaskDesc");
        String taskDateCreated = getIntent().getStringExtra("TaskCreated");
        String taskDateUpdated = getIntent().getStringExtra("TaskUpdated");
        */
        detailsTaskNameTV.setText(taskInfo.getName());
        detailsTaskDescTV.setText(taskInfo.getDescription());
        detailsTaskCreatedTV.setText(taskInfo.getDateCreated());
        detailsTaskUpdatedTV.setText(taskInfo.getDateUpdated());




        //**************************** For Back Button ***************************************************

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //**************************** End Back Button Work **********************************************


    }


    //******************For Menu******************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.actionDeleteId) {

            if(taskId!=0){
                taskDatabaseOperation.deleteTask(taskId);
                Intent intent = new Intent(TaskDetailsActivity.this,ViewTaskActivity.class);
                Toast.makeText(TaskDetailsActivity.this, "Task Deleted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }


        }
        else if (item.getItemId() == R.id.actionEditId) {

            if(taskId!=0){
                Intent intent = new Intent(TaskDetailsActivity.this,AddTaskActivity.class);
                intent.putExtra("TaskId",taskId);
                startActivity(intent);
            }


        }
        else if (item.getItemId() == android.R.id.home){ //For Back Button

            finish();
        }


        return super.onOptionsItemSelected(item);
    }

//******************End Menu Work******************************
}
