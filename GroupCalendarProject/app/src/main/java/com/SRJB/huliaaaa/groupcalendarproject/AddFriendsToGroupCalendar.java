package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class AddFriendsToGroupCalendar extends AppCompatActivity {

    Button finish2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends_to_group_calendar);
        finish2 = (Button) findViewById(R.id.finish2BTN);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("WHO WOULD YOU LIKE TO ADD?");


        finish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendsToGroupCalendar.this, GroupCalendars.class);
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

}
