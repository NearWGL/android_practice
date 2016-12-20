package com.near.calendar_schedual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    ArrayList<Task> tasks;
    Context context;
    LayoutInflater layoutInflater;

    public ListAdapter(ArrayList <Task> task , Context context, LayoutInflater layoutInflater) {
        this.tasks = task;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return  tasks.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.task_layout,parent,false);
        }

        Task task = getTask(position);
        Date date = new Date(task.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String str = simpleDateFormat.format(date.getTime());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(str);
        TextView message = (TextView) view.findViewById(R.id.task);
        message.setText(task.getMessage());

        return  view;
    }

    Task getTask(int position){
        return ((Task) getItem(position));
    }

    public @Override
    long getItemId(int position) {
        return position;
    }

    public @Override
    Object getItem(int position) {
        return tasks.get(position);
    }
}
