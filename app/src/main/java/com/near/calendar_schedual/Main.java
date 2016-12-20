package com.near.calendar_schedual;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Main extends AppCompatActivity {
    private CalendarView calendarView;
    private Calendar calendar;
    private Button today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        Calendar calendar = Calendar.getInstance();

        final int yy = calendar.get(Calendar.YEAR);
        final int mm = calendar.get(Calendar.MONTH);
        final int dd = calendar.get(Calendar.DAY_OF_MONTH);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Date date = getDate(year, month, dayOfMonth);

                Toast.makeText(Main.this, String.valueOf(dayOfMonth) , Toast.LENGTH_LONG).show();

                Intent intent  = new Intent(Main.this ,DayActivity.class);
                intent.putExtra("date",date.getTime());
                startActivity(intent);
            }
        });

        today = (Button) findViewById(R.id.today);

        final String day = "Today: " + new StringBuilder().append(dd).append(".").append(" ").
                append(mm + 1).append(".").append(" ").append(yy);

        today.setText(day);
        today.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Date date = getDate(yy , mm ,dd);

                Intent intent = new Intent(Main.this, DayActivity.class);
                intent.putExtra("day", date.getTime());
                startActivity(intent);
            }
        });

    }

    private Date getDate(int year, int month, int dayOfMonth) {
        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDate(dayOfMonth);
        return date;
    }

}
