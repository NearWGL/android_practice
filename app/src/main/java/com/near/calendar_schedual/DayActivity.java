package com.near.calendar_schedual;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("tasks");
    private FloatingActionButton fab;
    private ListAdapter listAdapter;
    private Date thisDay;
    private ListView listView;
    private TextView day;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_schedual_layout);

        intent = this.getIntent();

        thisDay = new Date(getIntent().getLongExtra("date",0));

        day = (TextView)findViewById(R.id.day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        String str = simpleDateFormat.format(thisDay.getTime());
        day.setText(str);

        fab = (FloatingActionButton) findViewById(R.id.add_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DayActivity.this, AddTaskActivity.class);
                intent.putExtra("day",DayActivity.this.getIntent().getLongExtra("date",0));
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.dayListView);
        listAdapter = new ListAdapter(readFromDb(), this, this.getLayoutInflater());
        listView.setAdapter(listAdapter);
    }

    private ArrayList<Task> readFromDb() {

        final ArrayList<Task> tasks = new ArrayList<>();

        String refName = thisDay.getDate() + " " + thisDay.getMonth() + " " +
                thisDay.getYear();

        reference.child(refName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                    new Thread() {
                        @Override
                        public void run() {
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                tasks.add(child.getValue(Task.class));
                            }
                        }
                    }.start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return tasks;
    }
}
