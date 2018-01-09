package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddFriendsToGroupCalendar extends AppCompatActivity {

    Button finish2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends_to_group_calendar);
        finish2 = (Button) findViewById(R.id.finish2BTN);

        finish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendsToGroupCalendar.this, GroupCalendars.class);
                startActivity(intent);
            }
        });
    }
}
