package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupCalendars extends AppCompatActivity {

    TextView groupTitle;
    Button addPpl;

    ListView addedFriends;
    ListView groupTitlesList;
    Button createGroup;
    Button finish;
    ArrayList<String> myGroupCalendarsList;
    ArrayAdapter<String> adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_calendars);

        groupTitlesList = (ListView) findViewById(R.id.groupCalendarTitleList);
        groupTitle = (TextView) findViewById(R.id.groupcalendartitle);
        addPpl = (Button) findViewById(R.id.addFriendstoCalendarBTN);
        addedFriends = (ListView) findViewById(R.id.friendsinCalendarList);
        createGroup = (Button) findViewById(R.id.createGroupCalendarBTN);
        finish = (Button) findViewById(R.id.finishBTN);

        myGroupCalendarsList = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(GroupCalendars.this, android.R.layout.simple_list_item_1,
                myGroupCalendarsList);
        groupTitlesList.setAdapter(adapter1);
        groupTitlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(GroupCalendars.this, GroupCalendarView.class);
                startActivity(intent);
            }
        });


        groupTitle.setEnabled(false);
        addPpl.setEnabled(false);
        addedFriends.setVisibility(View.INVISIBLE);
        finish.setVisibility(View.INVISIBLE);

        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupTitle.setEnabled(true);
                groupTitle.requestFocus();
                addPpl.setEnabled(true);
                addedFriends.setVisibility(View.VISIBLE);
                createGroup.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.VISIBLE);

                addPpl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intento = new Intent(GroupCalendars.this, AddFriendsToGroupCalendar.class);
                        startActivity(intento);
                    }
                });

                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String result = groupTitle.getText().toString();
                        myGroupCalendarsList.add(result);
                        adapter1.notifyDataSetChanged();
                        groupTitle.setText("");
                        groupTitle.setEnabled(false);
                        addPpl.setEnabled(false);
                        addedFriends.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.INVISIBLE);
                        createGroup.setVisibility(View.VISIBLE);

                    }
                });
            }
        });
    }
}
