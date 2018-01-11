package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

public class GroupCalendarView extends AppCompatActivity {

    CompactCalendarView groupCompactCalendar;
    Button createGroupEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_calendar_view);



        groupCompactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view2);
        createGroupEvent = (Button) findViewById(R.id.createGroupEvent);

        createGroupEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupCalendarView.this, com.SRJB.huliaaaa.groupcalendarproject.CreateGroupEvent.class);
                startActivity(intent);
            }
        });
    }
}
