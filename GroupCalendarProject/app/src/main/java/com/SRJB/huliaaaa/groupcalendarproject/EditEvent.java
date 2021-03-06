package com.SRJB.huliaaaa.groupcalendarproject;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class EditEvent extends AppCompatActivity {

    EditText Date;
    EditText Time;
    RadioButton pickTheDate;
    RadioButton pickTheTime;
    Button addevent;
    Button ok1;
    Button ok2;
    DatePicker DP;
    TimePicker TP;
    EditText TITLE;
    EditText DESCRIPTION;
    OurEvent Ourevent;
    ArrayList<OurEvent> ourEventArray;
    Event event;

    CheckBox noDes;
    RadioButton pickTheColor;
    EditText Colorpicked;
    CheckBox redBX;
    CheckBox cyanBX;
    CheckBox greenBX;
    CheckBox magentaBX;
    CheckBox blueBX;
    int colorPicked;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    boolean exists;
    FirebaseAuth firebaseAuth;
    String currentcal;
    String calendar;
    String eventtochange;
    String currentevent;

    int _color;
    long millis;
    Object title;

    int colordisplay;
    long millisdisplay;
    Object titledisplay;
    Boolean match;

    OurEvent NewEvent;

    ArrayList<Integer> colors;
    ArrayList<Object> datas;
    ArrayList<Long> timesinmillis;
    ArrayList<Object> descriptions;
    String _currenttheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _currenttheme = MainMenu.currenttheme;
        if (_currenttheme.contains("Green")) {

            setTheme(R.style.TestTheme1);
        } else if (_currenttheme.contains("Purple")) {

            setTheme(R.style.PurpDarkBoi);
        }
        else if (_currenttheme.contains("Yellow")) {

            setTheme(R.style.PurpDarkBoi_yellowpeeboi);
        }

        setContentView(R.layout.activity_edit_event);
        colors = new ArrayList<Integer>();
        datas = new ArrayList<Object>();
        timesinmillis = new ArrayList<Long>();
        descriptions = new ArrayList<Object>();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Event Creation");

        match = false;
        addevent = (Button) findViewById(R.id.add_btn2);

        Time = (EditText) findViewById(R.id.timepickd2);
        Date = (EditText) findViewById(R.id.datepickd2);
        Colorpicked = (EditText) findViewById(R.id.colorpickd2);
        pickTheDate = (RadioButton) findViewById(R.id.pickdate_btn2);
        pickTheTime = (RadioButton) findViewById(R.id.picktime_btn2);
        pickTheColor = (RadioButton) findViewById(R.id.pickColorBtn2);
        DP = (DatePicker ) findViewById(R.id.datePicker22);
        TP = (TimePicker) findViewById(R.id.timePicker22);
        ok1 = (Button) findViewById(R.id.ok12);
        ok2 = (Button) findViewById(R.id.ok22);
        TITLE = (EditText) findViewById(R.id.title_text2);
        DESCRIPTION = (EditText) findViewById(R.id.des_txt2);
        ourEventArray = new ArrayList<OurEvent>();
        noDes = (CheckBox) findViewById(R.id.noDes_checkbox2);
        redBX = (CheckBox) findViewById(R.id.redBox2);
        cyanBX  = (CheckBox) findViewById(R.id.cyanBox2);
        greenBX= (CheckBox) findViewById(R.id.greenBox2);
        magentaBX= (CheckBox) findViewById(R.id.magentaBox2);
        blueBX= (CheckBox) findViewById(R.id.blueBox2);

        ok1.setVisibility(View.INVISIBLE);
        ok2.setVisibility(View.INVISIBLE);
        Date.setVisibility(View.INVISIBLE);
        Time.setVisibility(View.INVISIBLE);

        Colorpicked.setVisibility(View.INVISIBLE);
        redBX.setVisibility(View.INVISIBLE);
        cyanBX.setVisibility(View.INVISIBLE);
        greenBX.setVisibility(View.INVISIBLE);
        magentaBX.setVisibility(View.INVISIBLE);
        blueBX.setVisibility(View.INVISIBLE);


        TITLE = (EditText) findViewById(R.id.title_text2);
        DESCRIPTION = (EditText) findViewById(R.id.des_txt2);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        ourEventArray = new ArrayList<OurEvent>();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        currentcal = MyCalendars.currentcal;
        calendar = "hi";


        pickTheDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent.setVisibility(View.INVISIBLE);
                Date.setVisibility(View.INVISIBLE);
                pickTheDate.setChecked(true);
                pickTheDate.setVisibility(View.INVISIBLE);
                pickTheTime.setVisibility(View.INVISIBLE);
                ok1.setVisibility(View.VISIBLE);
                DP.setVisibility(View.VISIBLE);
                TITLE.setVisibility(View.INVISIBLE);
                DESCRIPTION.setVisibility(View.INVISIBLE);
                Colorpicked.setVisibility(View.INVISIBLE);
                redBX.setVisibility(View.INVISIBLE);
                cyanBX.setVisibility(View.INVISIBLE);
                greenBX.setVisibility(View.INVISIBLE);
                magentaBX.setVisibility(View.INVISIBLE);
                blueBX.setVisibility(View.INVISIBLE);
                pickTheColor.setVisibility(View.INVISIBLE);
                Time.setVisibility(View.INVISIBLE);
                noDes.setVisibility(View.INVISIBLE);

                ok1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date.setVisibility(View.VISIBLE);
                        addevent.setVisibility(View.VISIBLE);

                        pickTheDate.setVisibility(View.VISIBLE);
                        pickTheTime.setVisibility(View.VISIBLE);
                        ok1.setVisibility(View.INVISIBLE);
                        DP.setVisibility(View.INVISIBLE);
                        TITLE.setVisibility(View.VISIBLE);
                        DESCRIPTION.setVisibility(View.VISIBLE);
                        noDes.setVisibility(View.VISIBLE);
                        pickTheColor.setVisibility(View.VISIBLE);

                        if (pickTheTime.isChecked())
                        {
                            Time.setVisibility(View.VISIBLE);
                        }
                        if (pickTheColor.isChecked())
                        {
                            Colorpicked.setVisibility(View.VISIBLE);
                        }


                        String Month = String.valueOf(DP.getMonth()+1);
                        String Day = String.valueOf(DP.getDayOfMonth());
                        String Year = String.valueOf(DP.getYear());
                        Date.setText(Month+"/"+Day + "/" + Year);
                    }
                });



            }
        });

        pickTheTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent.setVisibility(View.INVISIBLE);

                pickTheTime.setChecked(true);
                pickTheDate.setVisibility(View.INVISIBLE);
                pickTheTime.setVisibility(View.INVISIBLE);
                TP.setVisibility(View.VISIBLE);
                ok2.setVisibility(View.VISIBLE);
                TITLE.setVisibility(View.INVISIBLE);
                DESCRIPTION.setVisibility(View.INVISIBLE);
                Date.setVisibility(View.INVISIBLE);
                Time.setVisibility(View.INVISIBLE);
                Colorpicked.setVisibility(View.INVISIBLE);
                redBX.setVisibility(View.INVISIBLE);
                cyanBX.setVisibility(View.INVISIBLE);
                greenBX.setVisibility(View.INVISIBLE);
                magentaBX.setVisibility(View.INVISIBLE);
                blueBX.setVisibility(View.INVISIBLE);
                noDes.setVisibility(View.INVISIBLE);
                pickTheColor.setVisibility(View.INVISIBLE);

                ok2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Date.setVisibility(View.VISIBLE);
                        addevent.setVisibility(View.VISIBLE);

                        pickTheDate.setVisibility(View.VISIBLE);
                        pickTheTime.setVisibility(View.VISIBLE);
                        ok2.setVisibility(View.INVISIBLE);
                        TITLE.setVisibility(View.VISIBLE);
                        Time.setVisibility(View.VISIBLE);
                        DESCRIPTION.setVisibility(View.VISIBLE);
                        TP.setVisibility(View.INVISIBLE);
                        noDes.setVisibility(View.VISIBLE);
                        pickTheColor.setVisibility(View.VISIBLE);
                        //pickTheDate.setChecked(true);

                        redBX.setVisibility(View.VISIBLE);
                        cyanBX.setVisibility(View.VISIBLE);
                        greenBX.setVisibility(View.VISIBLE);
                        magentaBX.setVisibility(View.VISIBLE);
                        blueBX.setVisibility(View.VISIBLE);


                        String Month = String.valueOf(CalendarView.clickedDate.getMonth() + 1);
                        String Day = String.valueOf(CalendarView.clickedDate.getDate());
                        String Year = String.valueOf(CalendarView.clickedDate.getYear() + 1900);
                        Date.setText(Month+"/"+Day + "/" + Year);
                        Date.setVisibility(View.VISIBLE);


                        if (pickTheDate.isChecked())
                        {
                            Date.setVisibility(View.VISIBLE);
                        }
                        if (pickTheColor.isChecked())
                        {
                            Colorpicked.setVisibility(View.VISIBLE);
                        }
                        Time.setVisibility(View.VISIBLE);
                        String HOUR = String.valueOf(TP.getHour());
                        String MINUTE = String.valueOf(TP.getMinute());

                        if(TP.getMinute() < 10)
                        {
                            Time.setText(HOUR + ":0" + MINUTE);
                        }
                        else
                        {

                            Time.setText(HOUR + ":" + MINUTE);
                        }


                    }
                });
            }
        });
        pickTheColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBX.setVisibility(View.VISIBLE);
                cyanBX.setVisibility(View.VISIBLE);
                greenBX.setVisibility(View.VISIBLE);
                magentaBX.setVisibility(View.VISIBLE);
                blueBX.setVisibility(View.VISIBLE);

                redBX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked.setVisibility(View.VISIBLE);
                        Colorpicked.setText("Red");
                        cyanBX.setChecked(false);
                        greenBX.setChecked(false);
                        magentaBX.setChecked(false);
                        blueBX.setChecked(false);
                        colorPicked = Color.RED;
                    }
                });



                cyanBX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked.setVisibility(View.VISIBLE);
                        Colorpicked.setText("Cyan");
                        redBX.setChecked(false);
                        greenBX.setChecked(false);
                        magentaBX.setChecked(false);
                        blueBX.setChecked(false);
                        colorPicked = Color.CYAN;
                    }
                });



                greenBX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Colorpicked.setVisibility(View.VISIBLE);
                        Colorpicked.setText("Green");
                        cyanBX.setChecked(false);
                        redBX.setChecked(false);
                        magentaBX.setChecked(false);
                        blueBX.setChecked(false);
                        colorPicked = Color.GREEN;
                    }
                });


                magentaBX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Colorpicked.setVisibility(View.VISIBLE);
                        Colorpicked.setText("Magenta");
                        cyanBX.setChecked(false);
                        greenBX.setChecked(false);
                        redBX.setChecked(false);
                        blueBX.setChecked(false);
                        colorPicked = Color.MAGENTA;

                    }
                });


                blueBX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Colorpicked.setVisibility(View.VISIBLE);
                        Colorpicked.setText("Blue");
                        cyanBX.setChecked(false);
                        greenBX.setChecked(false);
                        magentaBX.setChecked(false);
                        redBX.setChecked(false);
                        colorPicked = Color.BLUE;
                    }
                });


            }
        });



///////

        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dess = DESCRIPTION.getText().toString();
                String titt = TITLE.getText().toString();

                if (noDes.isChecked()==false)
                {
                    if (TextUtils.isEmpty(dess))
                    {
                        DESCRIPTION.setError(getString(R.string.error_field_required));

                    }
                    else
                    {
                        if (blueBX.isChecked())
                        {
                            colorPicked = Color.BLUE;
                        }
                        else if (greenBX.isChecked())
                        {
                            colorPicked = Color.GREEN;
                        }
                        else if (redBX.isChecked())
                        {
                            colorPicked = Color.RED;
                        }
                        else if (cyanBX.isChecked())
                        {
                            colorPicked = Color.CYAN;
                        }
                        else if (magentaBX.isChecked())
                        {
                            colorPicked = Color.MAGENTA;
                        }
                        else
                        {
                            colorPicked = Color.BLACK;
                        }

                        String[] parts = Time.getText().toString().split(":");
                        int hour = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);

                        Date datee = new Date(CalendarView.clickedDate.getYear(), CalendarView.clickedDate.getMonth(), CalendarView.clickedDate.getDate(), hour, minutes);
                        long millisecondsS = datee.getTime();

                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        updateEventInfo();

                        DESCRIPTION.setText("");
                        pickTheTime.setChecked(false);
                        pickTheDate.setChecked(false);
                        TITLE.setText("");

                        finish();
                    }
                }
                else if (pickTheColor.isChecked() == false)
                {
                    colorPicked = Color.RED;
                }
                else if (TextUtils.isEmpty(titt))
                {
                    TITLE.setError(getString(R.string.error_field_required));
                }
                //else if (pickTheTime.isChecked() == false && !TextUtils.isEmpty(Time.getText().toString()))
                //{
                //    String[] parts = Time.getText().toString().split(":");
                //    int hour = Integer.parseInt(parts[0]);
                //    int minutes = Integer.parseInt(parts[1]);
                //
                //    java.util.Date datee = new Date(CalendarView.clickedDate.getYear(), CalendarView.clickedDate.getMonth(), CalendarView.clickedDate.getDate(), hour, minutes);
                //    long millisecondsS = datee.getTime();
//
                //    if (noDes.isChecked() && TextUtils.isEmpty(DESCRIPTION.getText().toString())) {
                //        if (blueBX.isChecked()) {
                //            colorPicked = Color.BLUE;
                //        } else if (greenBX.isChecked()) {
                //            colorPicked = Color.GREEN;
                //        } else if (redBX.isChecked()) {
                //            colorPicked = Color.RED;
                //        } else if (cyanBX.isChecked()) {
                //            colorPicked = Color.CYAN;
                //        } else if (magentaBX.isChecked()) {
                //            colorPicked = Color.MAGENTA;
                //        }
//
                //        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), "");
                //        ourEventArray.add(Ourevent);
                //        event = new Event(colorPicked, millisecondsS, TITLE.getText().toString());
                //        updateEventInfo();
//
                //    }
                //}
                else if (pickTheDate.isChecked()== false && TextUtils.isEmpty(Date.getText().toString()))
                {
                    pickTheDate.setError(getString(R.string.error_field_required));
                }
                else if (pickTheDate.isChecked() == false && !TextUtils.isEmpty(Date.getText().toString()) && pickTheTime.isChecked() == false && !TextUtils.isEmpty(Time.getText().toString()))
                {
                    String[] parts = Time.getText().toString().split(":");
                    int hour = Integer.parseInt(parts[0]);
                    int minutes = Integer.parseInt(parts[1]);
                    java.util.Date datee = new Date(CalendarView.clickedDate.getYear(), CalendarView.clickedDate.getMonth(), CalendarView.clickedDate.getDate(), hour, minutes);
                    long millisecondsS = datee.getTime();

                    if (noDes.isChecked() && TextUtils.isEmpty(DESCRIPTION.getText().toString()))
                    {
                        if (blueBX.isChecked())
                        {
                            colorPicked = Color.BLUE;
                        }
                        else if (greenBX.isChecked())
                        {
                            colorPicked = Color.GREEN;
                        }
                        else if (redBX.isChecked())
                        {
                            colorPicked = Color.RED;
                        }
                        else if (cyanBX.isChecked())
                        {
                            colorPicked = Color.CYAN;
                        }
                        else if (magentaBX.isChecked())
                        {
                            colorPicked = Color.MAGENTA;
                        }

                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), "");
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        updateEventInfo();

                    }
                    else
                    {
                        java.util.Date dateee = new Date(CalendarView.clickedDate.getYear(), CalendarView.clickedDate.getMonth(), CalendarView.clickedDate.getDate(), TP.getHour(), TP.getMinute());
                        long millisecondsSs = dateee.getTime();

                        if (blueBX.isChecked())
                        {
                            colorPicked = Color.BLUE;
                            magentaBX.setChecked(false);
                            greenBX.setChecked(false);
                            cyanBX.setChecked(false);
                            redBX.setChecked(false);
                        }
                        else if (greenBX.isChecked())
                        {
                            colorPicked = Color.GREEN;
                            magentaBX.setChecked(false);
                            blueBX.setChecked(false);
                            cyanBX.setChecked(false);
                            redBX.setChecked(false);
                        }
                        else if (redBX.isChecked())
                        {
                            colorPicked = Color.RED;
                            magentaBX.setChecked(false);
                            greenBX.setChecked(false);
                            blueBX.setChecked(false);
                            cyanBX.setChecked(false);
                        }
                        else if (cyanBX.isChecked())
                        {
                            colorPicked = Color.CYAN;
                            magentaBX.setChecked(false);
                            greenBX.setChecked(false);
                            blueBX.setChecked(false);
                            redBX.setChecked(false);
                        }
                        else if (magentaBX.isChecked())
                        {
                            colorPicked = Color.MAGENTA;
                            greenBX.setChecked(false);
                            blueBX.setChecked(false);
                            cyanBX.setChecked(false);
                            redBX.setChecked(false);
                        }

                        Ourevent = new OurEvent(colorPicked, millisecondsSs, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsSs,TITLE.getText().toString() );
                        updateEventInfo();

                    }

                    DESCRIPTION.setText("");
                    pickTheTime.setChecked(false);
                    pickTheDate.setChecked(false);
                    TITLE.setText("");

                    finish();
                }
                else
                {

                    Date datee = new Date(DP.getYear()-1900, DP.getMonth(), DP.getDayOfMonth(), TP.getHour(), TP.getMinute());
                    long millisecondsS = datee.getTime();

                    if (noDes.isChecked() && TextUtils.isEmpty(Date.getText().toString()))
                    {
                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), "");
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        updateEventInfo();
                    }
                    else
                    {
                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        updateEventInfo();
                    }
                    //compactCalendar.addEvent(event);

                    //Intent intent = new Intent(CreateEvent.this, CalendarView.class);
                    //startActivity(intent);



                    DESCRIPTION.setText("");
                    pickTheTime.setChecked(false);
                    pickTheDate.setChecked(false);
                    TITLE.setText("");

                    finish();
                }

            }
        });

        databaseReference.child("users").child(_email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    if (child.getKey().toString().contains("CurrentCal"))
                        currentcal = child.getValue().toString();
                    // arrayList = firebaselist;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        databaseReference.child("users").child(_email).child("Events").child(currentcal).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    currentevent = child.getKey();
                    Iterable<DataSnapshot> children2 = child.getChildren();
                    for (DataSnapshot child2 : children2) {
                        if (child2.getKey().toString().contains("color") && child2.exists()) {
                            colors.add(child2.getValue(Integer.class));
                        } else if (child2.getKey().toString().contains("data")) {
                            datas.add(child2.getValue());
                            if (child2.getValue().toString().contains(CalendarView.currenteventdata))
                            {

                                eventtochange = currentevent;
                                match = true;
                                TITLE.setText(CalendarView.currenteventdata);
                                //noDes.setChecked(true);
                                //pickTheDate.setChecked(true);
                                pickTheColor.setChecked(true);
                                colordisplay = colors.get(Integer.parseInt(currentevent));

                                String Month = String.valueOf(CalendarView.clickedDate.getMonth() + 1);
                                String Day = String.valueOf(CalendarView.clickedDate.getDate());
                                String Year = String.valueOf(CalendarView.clickedDate.getYear() + 1900);
                                Date.setText(Month+"/"+Day + "/" + Year);
                                Date.setVisibility(View.VISIBLE);

                                Time.setVisibility(View.VISIBLE);


                                redBX.setVisibility(View.VISIBLE);
                                cyanBX.setVisibility(View.VISIBLE);
                                greenBX.setVisibility(View.VISIBLE);
                                magentaBX.setVisibility(View.VISIBLE);
                                blueBX.setVisibility(View.VISIBLE);

                                if (colordisplay == -16711936)
                                {
                                    greenBX.setChecked(true);
                                    magentaBX.setChecked(false);
                                    blueBX.setChecked(false);
                                    cyanBX.setChecked(false);
                                    redBX.setChecked(false);
                                }
                                else if (colordisplay == -65281)
                                {
                                    magentaBX.setChecked(true);
                                    greenBX.setChecked(false);
                                    blueBX.setChecked(false);
                                    cyanBX.setChecked(false);
                                    redBX.setChecked(false);
                                }
                                else if (colordisplay == -16776961)
                                {
                                    blueBX.setChecked(true);
                                    magentaBX.setChecked(false);
                                    greenBX.setChecked(false);
                                    cyanBX.setChecked(false);
                                    redBX.setChecked(false);
                                }
                                else if (colordisplay == -16711681)
                                {
                                    cyanBX.setChecked(true);
                                    magentaBX.setChecked(false);
                                    greenBX.setChecked(false);
                                    blueBX.setChecked(false);
                                    redBX.setChecked(false);
                                }
                                else if (colordisplay == -65536)
                                {
                                    redBX.setChecked(true);
                                    magentaBX.setChecked(false);
                                    greenBX.setChecked(false);
                                    blueBX.setChecked(false);
                                    cyanBX.setChecked(false);
                                }
                                else
                                {
                                    colorPicked = Color.BLACK;
                                }
                            }
                        }
                        else if (child2.getKey().toString().contains("description"))
                        {
                            descriptions.add(child2.getValue());
                            if (child2.getValue().toString().equals(""))
                            {
                                noDes.setChecked(true);
                            }
                            else
                            {
                                noDes.setChecked(false);
                            }
                            DESCRIPTION.setText(child2.getValue().toString());
                        }
                        else if (child2.getKey().toString().contains("timeInMillis"))
                        {
                            timesinmillis.add(child2.getValue(Long.class));
                            Date date = new Date(child2.getValue((Long.class)));

                            Time.setText(date.getHours() + ":" + date.getMinutes());


                        }

                    }
                    ourEventArray.clear();
                    for (int x=0; x<colors.size(); x++) {

                        NewEvent = new OurEvent(colors.get(x), timesinmillis.get(x), datas.get(x), descriptions.get(x));
                        ourEventArray.add(NewEvent);
                    }



                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        /////////////////
        //if (eventtochange != null) {
        //    databaseReference.child("users").child(_email).child("Events").child(currentcal).child(eventtochange).addValueEventListener(new ValueEventListener() {
        //        @Override
        //        public void onDataChange(DataSnapshot dataSnapshot) {
        //            // This method is called once with the initial value and again
        //            // whenever data at this location is updated
//
        //            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
        //            for (DataSnapshot child : children) {
        //                if (child.getKey().toString().contains("color")) {
        //                    colordisplay = child.getValue(Integer.class);
        //                } else if (child.getKey().toString().contains("data")) {
        //                    titledisplay = child.getValue();
        //                } else if (child.getKey().toString().contains("timeInMillis")) {
        //                    millisdisplay = child.getValue(Long.class);
        //                }
        //            }
        //        }
//
        //        @Override
        //        public void onCancelled(DatabaseError error) {
        //            // Failed to read value
//
        //        }
        //    });
        //}

        //TITLE.setText(titledisplay.toString());
    }



    private void updateEventInfo()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("Events").child(currentcal).child(eventtochange).setValue(Ourevent);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        Toast.makeText(this, "Event Updated...", Toast.LENGTH_SHORT).show();
    }


}

