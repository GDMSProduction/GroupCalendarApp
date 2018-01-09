package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateGroupEvent extends AppCompatActivity {

    EditText Date2;
    EditText Time2;
    RadioButton pickTheDate2;
    RadioButton pickTheTime2;
    Button addevent2;
    Button ok12;
    Button ok22;
    DatePicker DP2;
    TimePicker TP2;
    EditText TITLE2;
    EditText DESCRIPTION2;
    GroupEvent groupEvent;
    ArrayList<String> friendsinvitedList;
    ListView invitedFriends;
    ListView friendsToInviteLV;
    Event event2;
    CheckBox noDes2;
    RadioButton pickTheColor2;
    EditText Colorpicked2;
    CheckBox redBX2;
    CheckBox cyanBX2;
    CheckBox greenBX2;
    CheckBox magentaBX2;
    CheckBox yellowBX2;
    int colorPicked2;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    List<String> friendsToInvite = new ArrayList<>(Arrays.asList("xyz", "abc"));
    ArrayList<GroupEvent> ourGroupEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_event);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Group Event Creation");

        addevent2 = (Button) findViewById(R.id.add_btn2);
        Time2 = (EditText) findViewById(R.id.timepickd2);
        Date2 = (EditText) findViewById(R.id.datepickd2);
        Colorpicked2 = (EditText) findViewById(R.id.colorpickd2);
        pickTheDate2 = (RadioButton) findViewById(R.id.pickdate_btn2);
        pickTheTime2 = (RadioButton) findViewById(R.id.picktime_btn2);
        pickTheColor2 = (RadioButton) findViewById(R.id.pickColorBtn2);
        DP2 = (DatePicker ) findViewById(R.id.groupDatePicker);
        TP2 = (TimePicker) findViewById(R.id.groupTimePicker);
        ok12 = (Button) findViewById(R.id.ok1);
        ok22 = (Button) findViewById(R.id.ok2);
        TITLE2 = (EditText) findViewById(R.id.title_text2);
        DESCRIPTION2 = (EditText) findViewById(R.id.des_txt2);
        noDes2 = (CheckBox) findViewById(R.id.noDes_checkbox2);
        redBX2 = (CheckBox) findViewById(R.id.redBox2);
        cyanBX2  = (CheckBox) findViewById(R.id.cyanBox2);
        greenBX2= (CheckBox) findViewById(R.id.greenBox2);
        magentaBX2= (CheckBox) findViewById(R.id.magentaBox2);
        yellowBX2= (CheckBox) findViewById(R.id.yellowBox2);
        invitedFriends = (ListView) findViewById(R.id.friendsToInvite2);
        friendsToInviteLV = (ListView) findViewById(R.id.friendsToInvite2);

        friendsinvitedList = new ArrayList<String>();
        friendsToInvite = new ArrayList<String>();
        ourGroupEvents = new ArrayList<GroupEvent>();


       adapter3 = new ArrayAdapter<String>(CreateGroupEvent.this, android.R.layout.simple_list_item_1,
              friendsToInvite);
       friendsToInviteLV.setAdapter(adapter3);


        friendsToInvite.add("Bob");
        adapter3.notifyDataSetChanged();


        adapter2 = new ArrayAdapter<String>(CreateGroupEvent.this, android.R.layout.simple_list_item_1,
                friendsinvitedList);
        invitedFriends.setAdapter(adapter2);

        // set friendsToInvite as the same array as friendsinCalendarList from GroupCalendars page
        friendsToInviteLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              friendsinvitedList.add(friendsToInviteLV.getSelectedItem().toString());
                adapter2.notifyDataSetChanged();

            }
        });

        ok12.setVisibility(View.INVISIBLE);
        ok22.setVisibility(View.INVISIBLE);
        Date2.setVisibility(View.INVISIBLE);
        Time2.setVisibility(View.INVISIBLE);
        Colorpicked2.setVisibility(View.INVISIBLE);
        redBX2.setVisibility(View.INVISIBLE);
        cyanBX2.setVisibility(View.INVISIBLE);
        greenBX2.setVisibility(View.INVISIBLE);
        magentaBX2.setVisibility(View.INVISIBLE);
        yellowBX2.setVisibility(View.INVISIBLE);

        pickTheDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent2.setVisibility(View.INVISIBLE);
                pickTheDate2.setChecked(true);
                pickTheDate2.setVisibility(View.INVISIBLE);
                pickTheTime2.setVisibility(View.INVISIBLE);
                ok12.setVisibility(View.VISIBLE);
                DP2.setVisibility(View.VISIBLE);
                TITLE2.setVisibility(View.INVISIBLE);
                DESCRIPTION2.setVisibility(View.INVISIBLE);
                Colorpicked2.setVisibility(View.INVISIBLE);
                redBX2.setVisibility(View.INVISIBLE);
                cyanBX2.setVisibility(View.INVISIBLE);
                greenBX2.setVisibility(View.INVISIBLE);
                magentaBX2.setVisibility(View.INVISIBLE);
                yellowBX2.setVisibility(View.INVISIBLE);
                noDes2.setVisibility(View.INVISIBLE);
                pickTheColor2.setVisibility(View.INVISIBLE);
                Time2.setVisibility(View.INVISIBLE);

                ok12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date2.setVisibility(View.VISIBLE);
                        addevent2.setVisibility(View.VISIBLE);
                        pickTheDate2.setVisibility(View.VISIBLE);
                        pickTheTime2.setVisibility(View.VISIBLE);
                        ok12.setVisibility(View.INVISIBLE);
                        DP2.setVisibility(View.INVISIBLE);
                        TITLE2.setVisibility(View.VISIBLE);
                        DESCRIPTION2.setVisibility(View.VISIBLE);
                        noDes2.setVisibility(View.VISIBLE);
                        pickTheColor2.setVisibility(View.VISIBLE);
                        if (pickTheTime2.isChecked())
                        {
                            Time2.setVisibility(View.VISIBLE);
                        }
                        if (pickTheColor2.isChecked())
                        {
                            Colorpicked2.setVisibility(View.VISIBLE);
                        }


                        String Month = String.valueOf(DP2.getMonth()+1);
                        String Day = String.valueOf(DP2.getDayOfMonth());
                        String Year = String.valueOf(DP2.getYear());
                        Date2.setText(Month+"/"+Day + "/" + Year);
                    }
                });



            }
        });

        pickTheTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent2.setVisibility(View.INVISIBLE);
                pickTheTime2.setChecked(true);
                pickTheDate2.setVisibility(View.INVISIBLE);
                pickTheTime2.setVisibility(View.INVISIBLE);
                TP2.setVisibility(View.VISIBLE);
                ok22.setVisibility(View.VISIBLE);
                TITLE2.setVisibility(View.INVISIBLE);
                DESCRIPTION2.setVisibility(View.INVISIBLE);
                Date2.setVisibility(View.INVISIBLE);
                Time2.setVisibility(View.VISIBLE);
                Colorpicked2.setVisibility(View.INVISIBLE);
                redBX2.setVisibility(View.INVISIBLE);
                cyanBX2.setVisibility(View.INVISIBLE);
                greenBX2.setVisibility(View.INVISIBLE);
                magentaBX2.setVisibility(View.INVISIBLE);
                yellowBX2.setVisibility(View.INVISIBLE);
                noDes2.setVisibility(View.INVISIBLE);
                pickTheColor2.setVisibility(View.INVISIBLE);

                ok22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Date.setVisibility(View.VISIBLE);
                        addevent2.setVisibility(View.VISIBLE);
                        pickTheDate2.setVisibility(View.VISIBLE);
                        pickTheTime2.setVisibility(View.VISIBLE);
                        ok22.setVisibility(View.INVISIBLE);
                        TITLE2.setVisibility(View.VISIBLE);
                        DESCRIPTION2.setVisibility(View.VISIBLE);
                        TP2.setVisibility(View.INVISIBLE);
                        noDes2.setVisibility(View.VISIBLE);
                        pickTheColor2.setVisibility(View.VISIBLE);


                        if (pickTheDate2.isChecked())
                        {
                            Date2.setVisibility(View.VISIBLE);
                        }
                        if (pickTheColor2.isChecked())
                        {
                            Colorpicked2.setVisibility(View.VISIBLE);
                        }
                        Time2.setVisibility(View.VISIBLE);
                        String HOUR = String.valueOf(TP2.getHour());
                        String MINUTE = String.valueOf(TP2.getMinute());

                        Time2.setText(HOUR + ":" + MINUTE);


                    }
                });
            }
        });
        pickTheColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBX2.setVisibility(View.VISIBLE);
                cyanBX2.setVisibility(View.VISIBLE);
                greenBX2.setVisibility(View.VISIBLE);
                magentaBX2.setVisibility(View.VISIBLE);
                yellowBX2.setVisibility(View.VISIBLE);

                redBX2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked2.setVisibility(View.VISIBLE);
                        Colorpicked2.setText("Red");
                        cyanBX2.setChecked(false);
                        greenBX2.setChecked(false);
                        magentaBX2.setChecked(false);
                        yellowBX2.setChecked(false);
                        colorPicked2 = Color.RED;
                    }
                });



                cyanBX2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked2.setVisibility(View.VISIBLE);
                        Colorpicked2.setText("Cyan");
                        redBX2.setChecked(false);
                        greenBX2.setChecked(false);
                        magentaBX2.setChecked(false);
                        yellowBX2.setChecked(false);
                        colorPicked2 = Color.CYAN;
                    }
                });



                greenBX2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked2.setVisibility(View.VISIBLE);
                        Colorpicked2.setText("Green");
                        cyanBX2.setChecked(false);
                        redBX2.setChecked(false);
                        magentaBX2.setChecked(false);
                        yellowBX2.setChecked(false);
                        colorPicked2 = Color.GREEN;
                    }
                });


                magentaBX2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Colorpicked2.setVisibility(View.VISIBLE);
                        Colorpicked2.setText("Magenta");
                        cyanBX2.setChecked(false);
                        greenBX2.setChecked(false);
                        redBX2.setChecked(false);
                        yellowBX2.setChecked(false);
                        colorPicked2 = Color.MAGENTA;

                    }
                });


                yellowBX2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Colorpicked2.setVisibility(View.VISIBLE);
                        Colorpicked2.setText("Yellow");
                        cyanBX2.setChecked(false);
                        greenBX2.setChecked(false);
                        magentaBX2.setChecked(false);
                        redBX2.setChecked(false);
                        colorPicked2 = Color.YELLOW;
                    }
                });


            }
        });

        addevent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dess = DESCRIPTION2.getText().toString();
                String titt = TITLE2.getText().toString();
                ArrayList<String> frends = friendsinvitedList;

                if (noDes2.isChecked()==false)
                {
                    if (TextUtils.isEmpty(dess))
                    {
                        DESCRIPTION2.setError(getString(R.string.error_field_required));

                    }
                }
                else if (pickTheColor2.isChecked() == false)
                {
                    colorPicked2 = Color.RED;
                }
                else if (TextUtils.isEmpty(titt))
                {
                    TITLE2.setError(getString(R.string.error_field_required));
                }
                else if (pickTheTime2.isChecked() == false)
                {
                    pickTheTime2.setError(getString(R.string.error_field_required));
                }
                else if (pickTheDate2.isChecked()== false)
                {
                    pickTheDate2.setError(getString(R.string.error_field_required));
                }
                else
                {

                    Date datee = new Date(DP2.getYear()-1900, DP2.getMonth(), DP2.getDayOfMonth(), TP2.getHour(), TP2.getMinute());
                    long millisecondsS = datee.getTime();

                    if (noDes2.isChecked())
                    {
                        if(frends.isEmpty())
                        {
                            frends = null;
                            groupEvent = new GroupEvent(colorPicked2,millisecondsS,TITLE2.getText().toString(), frends);
                            ourGroupEvents.add(groupEvent);
                            event2 = new Event(colorPicked2, millisecondsS,TITLE2.getText().toString() );
                        }
                        else
                        {
                            groupEvent = new GroupEvent(colorPicked2, millisecondsS, TITLE2.getText().toString(), frends);
                            ourGroupEvents.add(groupEvent);
                            event2 = new Event(colorPicked2, millisecondsS,TITLE2.getText().toString() );
                        }

                    }
                    else
                    {
                        if(frends.isEmpty())
                        {
                            frends = null;
                            groupEvent = new GroupEvent(colorPicked2,millisecondsS,TITLE2.getText().toString(), DESCRIPTION2.getText().toString(),frends);
                            ourGroupEvents.add(groupEvent);
                            event2 = new Event(colorPicked2, millisecondsS,TITLE2.getText().toString() );
                        }
                        else
                        {
                            groupEvent = new GroupEvent(colorPicked2, millisecondsS, TITLE2.getText().toString(),DESCRIPTION2.getText().toString(), frends);
                            ourGroupEvents.add(groupEvent);
                            event2 = new Event(colorPicked2, millisecondsS,TITLE2.getText().toString() );
                        }

                    }
                    //compactCalendar.addEvent(event);

                    Intent intent = new Intent(CreateGroupEvent.this, GroupCalendarView.class);
                    startActivity(intent);


                    DESCRIPTION2.setText("");
                    pickTheTime2.setChecked(false);
                    pickTheDate2.setChecked(false);
                    TITLE2.setText("");

                }

            }
        });
    }
}
