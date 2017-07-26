package com.example.sharifahmed.simpletodoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sharifahmed.simpletodoapp.Activity.TaskDetailsActivity;
import com.example.sharifahmed.simpletodoapp.Model.TaskInfo;
import com.example.sharifahmed.simpletodoapp.R;

import java.util.ArrayList;

/**
 * Created by SHARIF AHMED on 07/25/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    ArrayList<TaskInfo> taskInfoList;
    Context context;

    public RecyclerAdapter(ArrayList<TaskInfo> taskInfoList, Context context) {

        this.taskInfoList=taskInfoList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,taskInfoList);


        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.taskNameTV.setText(taskInfoList.get(position).getName());
        holder.taskDateCreatedTV.setText(taskInfoList.get(position).getDateCreated());
        holder.taskDateUpdatedTV.setText(taskInfoList.get(position).getDateUpdated());


    }

    @Override
    public int getItemCount() {
        return taskInfoList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView taskNameTV;
        TextView taskDateCreatedTV;
        TextView taskDateUpdatedTV;

        LinearLayout clickForDetails;
        ArrayList<TaskInfo> taskInfoLists = new ArrayList<TaskInfo>();
        Context context;

        public RecyclerViewHolder(View itemView, Context context, ArrayList<TaskInfo> taskInfoLists) {

            super(itemView);
            this.taskInfoLists = taskInfoLists;
            this.context = context;

            itemView.setOnClickListener(this);//Register The OnClickListener Interface

            taskNameTV = (TextView) itemView.findViewById(R.id.taskNameCustomId);
            taskDateCreatedTV = (TextView) itemView.findViewById(R.id.taskCustomDateCreatedId);
            taskDateUpdatedTV = (TextView) itemView.findViewById(R.id.taskCustomDateUpdatedId);

            clickForDetails = (LinearLayout) itemView.findViewById(R.id.clickForDetailsId);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            TaskInfo taskInfo = this.taskInfoLists.get(position);

            Intent taskDetails = new Intent(this.context, TaskDetailsActivity.class);

            taskDetails.putExtra("TaskId",taskInfo.getId());
            taskDetails.putExtra("TaskName",taskInfo.getName());
            taskDetails.putExtra("TaskDesc",taskInfo.getDescription());
            taskDetails.putExtra("TaskCreated",taskInfo.getDateCreated());
            taskDetails.putExtra("TaskUpdated",taskInfo.getDateUpdated());

            this.context.startActivity(taskDetails);

        }
    }
}
