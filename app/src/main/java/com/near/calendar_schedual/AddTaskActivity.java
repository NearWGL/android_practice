package com.near.calendar_schedual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class AddTaskActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("tasks");
    FloatingActionButton fab;
    TimePicker timePicker;
    EditText editText;
    Date date;
    Date thisDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        date = new Date();
        thisDay = new Date(getIntent().getLongExtra("day",0));

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                date.setTime(timePicker.getDrawingTime());
            }
        });
        editText = (EditText)findViewById(R.id.task_message);

        fab =(FloatingActionButton)findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Task task = new Task(
                        timePicker.getDrawingTime(),
                        AddTaskActivity.this.editText.getText().toString()
                );

                final String refName = thisDay.getDate() + " " + thisDay.getMonth() + " " +
                        thisDay.getYear();

                new Thread() {
                    @Override
                    public void run() {
                        reference.child(refName)
                                .child(String.valueOf(task.hashCode()))
                                .setValue(task);
                    }
                }.start();


//                Toast.makeText(AddTaskActivity.this
//                        ,task.toString()
//                        ,Toast.LENGTH_LONG).show();
            }
        });
    }
}
