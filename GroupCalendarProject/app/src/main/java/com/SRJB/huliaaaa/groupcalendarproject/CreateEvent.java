package com.SRJB.huliaaaa.groupcalendarproject;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

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

public class CreateEvent extends AppCompatActivity {

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

    int _color;
    long millis;
    Object title;


    OurEvent NewEvent;

    ArrayList<Integer> colors;
    ArrayList<Object> datas;
    ArrayList<Long> timesinmillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        colors = new ArrayList<Integer>();
        datas = new ArrayList<Object>();
        timesinmillis = new ArrayList<Long>();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Event Creation");

        addevent = (Button) findViewById(R.id.add_btn);
        Time = (EditText) findViewById(R.id.timepickd);
        Date = (EditText) findViewById(R.id.datepickd);
        Colorpicked = (EditText) findViewById(R.id.colorpickd);
        pickTheDate = (RadioButton) findViewById(R.id.pickdate_btn);
        pickTheTime = (RadioButton) findViewById(R.id.picktime_btn);
        pickTheColor = (RadioButton) findViewById(R.id.pickColorBtn);
        DP = (DatePicker ) findViewById(R.id.datePicker2);
        TP = (TimePicker) findViewById(R.id.timePicker2);
        ok1 = (Button) findViewById(R.id.ok1);
        ok2 = (Button) findViewById(R.id.ok2);
        TITLE = (EditText) findViewById(R.id.title_text);
        DESCRIPTION = (EditText) findViewById(R.id.des_txt);
        ourEventArray = new ArrayList<OurEvent>();
        noDes = (CheckBox) findViewById(R.id.noDes_checkbox);
        redBX = (CheckBox) findViewById(R.id.redBox);
        cyanBX  = (CheckBox) findViewById(R.id.cyanBox);
        greenBX= (CheckBox) findViewById(R.id.greenBox);
        magentaBX= (CheckBox) findViewById(R.id.magentaBox);
        blueBX= (CheckBox) findViewById(R.id.blueBox);

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


        TITLE = (EditText) findViewById(R.id.title_text);
        DESCRIPTION = (EditText) findViewById(R.id.des_txt);

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
                Time.setVisibility(View.VISIBLE);
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
                        DESCRIPTION.setVisibility(View.VISIBLE);
                        TP.setVisibility(View.INVISIBLE);
                        noDes.setVisibility(View.VISIBLE);
                        pickTheColor.setVisibility(View.VISIBLE);


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
                }
                else if (pickTheColor.isChecked() == false)
                {
                    colorPicked = Color.RED;
                }
                 else if (TextUtils.isEmpty(titt))
                {
                    TITLE.setError(getString(R.string.error_field_required));
                }
                else if (pickTheTime.isChecked() == false)
                {
                    pickTheTime.setError(getString(R.string.error_field_required));
                }
                else if (pickTheDate.isChecked()== false)
                {
                    pickTheDate.setError(getString(R.string.error_field_required));
                }
                else
                {

                    Date datee = new Date(DP.getYear()-1900, DP.getMonth(), DP.getDayOfMonth(), TP.getHour(), TP.getMinute());
                    long millisecondsS = datee.getTime();

                    if (noDes.isChecked())
                    {
                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString());
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        saveEventInfo();
                    }
                    else
                    {
                        Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                        ourEventArray.add(Ourevent);
                        event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                        saveEventInfo();
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
                   Iterable<DataSnapshot> children2 = child.getChildren();
                for (DataSnapshot child2 : children2) {
                    if (child2.getKey().toString().contains("color") && child2.exists()) {
                        colors.add(child2.getValue(Integer.class));
                    } else if (child2.getKey().toString().contains("data")) {
                        datas.add(child2.getValue());
                    } else if (child2.getKey().toString().contains("timeInMillis")) {
                        timesinmillis.add(child2.getValue(Long.class));
                    }

                }
                ourEventArray.clear();
                    for (int x=0; x<colors.size(); x++) {
                        NewEvent = new OurEvent(colors.get(x), timesinmillis.get(x), datas.get(x));
                        ourEventArray.add(NewEvent); /////////////this is fucked
                    }



               }
           }

           @Override
           public void onCancelled(DatabaseError error) {
               // Failed to read value

           }
       });


    }

    private void saveEventInfo()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("Events").child(currentcal).setValue(ourEventArray);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        Toast.makeText(this, "Event Saved...", Toast.LENGTH_SHORT).show();
    }

}
