package com.example.sharifahmed.simpletodoapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sharifahmed.simpletodoapp.Adapter.RecyclerAdapter;
import com.example.sharifahmed.simpletodoapp.DatabaseOperation.TaskDatabaseOperation;
import com.example.sharifahmed.simpletodoapp.R;

public class ViewTaskActivity extends AppCompatActivity {

    TaskDatabaseOperation taskDatabaseOperation;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageView emptyImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        emptyImageView = (ImageView) findViewById(R.id.emptyImageViewId);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        taskDatabaseOperation = new TaskDatabaseOperation(this);

        int totalContact = taskDatabaseOperation.getAllTask().size();

        if(totalContact != 0){
            recyclerAdapter = new RecyclerAdapter(taskDatabaseOperation.getAllTask(),this);
            recyclerView.setAdapter(recyclerAdapter);
        }
        else {

            emptyImageView.setImageResource(R.drawable.empty);
            Toast.makeText(ViewTaskActivity.this, "There Is No Data To Be Display", Toast.LENGTH_LONG).show();

        }

    }














    //******************For Menu******************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.actionAddId) {

            Intent intent = new Intent(ViewTaskActivity.this,AddTaskActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

//******************End Menu Work******************************


}
