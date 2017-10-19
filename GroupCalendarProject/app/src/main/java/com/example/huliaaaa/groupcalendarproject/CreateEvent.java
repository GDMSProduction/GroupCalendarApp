package com.example.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    boolean exists;
    FirebaseAuth firebaseAuth;
    String currentcal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        addevent = (Button) findViewById(R.id.add_btn);
        Time = (EditText) findViewById(R.id.timepickd);
        Date = (EditText) findViewById(R.id.datepickd);
        pickTheDate = (RadioButton) findViewById(R.id.pickdate_btn);
        pickTheTime = (RadioButton) findViewById(R.id.picktime_btn);
        DP = (DatePicker ) findViewById(R.id.datePicker2);
        TP = (TimePicker) findViewById(R.id.timePicker2);
        ok1 = (Button) findViewById(R.id.ok1);
        ok2 = (Button) findViewById(R.id.ok2);
        ok1.setVisibility(View.INVISIBLE);
        ok2.setVisibility(View.INVISIBLE);
        Date.setVisibility(View.INVISIBLE);
        Time.setVisibility(View.INVISIBLE);
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
        currentcal = "";

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
                        if (pickTheTime.isChecked())
                        {
                            Time.setVisibility(View.VISIBLE);
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


                        if (pickTheDate.isChecked())
                        {
                            Date.setVisibility(View.VISIBLE);
                        }
                        Time.setVisibility(View.VISIBLE);
                        String HOUR = String.valueOf(TP.getHour());
                        String MINUTE = String.valueOf(TP.getMinute());

                        Time.setText(HOUR + ":" + MINUTE);


                    }
                });
            }
        });

        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dess = DESCRIPTION.getText().toString();
                String titt = TITLE.getText().toString();

                if (TextUtils.isEmpty(dess))
                {
                    DESCRIPTION.setError(getString(R.string.error_field_required));

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



                    Ourevent = new OurEvent(Color.RED, millisecondsS, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                    ourEventArray.add(Ourevent);
                    event = new Event(Color.RED, millisecondsS,TITLE.getText().toString() );
                    //compactCalendar.addEvent(event);

                    Intent intent = new Intent(CreateEvent.this, CalendarView.class);
                    startActivity(intent);


                    DESCRIPTION.setText("");
                    pickTheTime.setChecked(false);
                    pickTheDate.setChecked(false);
                    TITLE.setText("");

                }

            }
        });

       //databaseReference.child("users").child(_email).child("CurrentCal").addValueEventListener(new ValueEventListener() {
       //    @Override
       //    public void onDataChange(DataSnapshot dataSnapshot) {
       //        // This method is called once with the initial value and again
       //        // whenever data at this location is updated

       //        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

       //        for (DataSnapshot child: children) {
       //            currentcal = child.getValue().toString();
       //            // arrayList = firebaselist;
       //        }
       //    }

       //    @Override
       //    public void onCancelled(DatabaseError error) {
       //        // Failed to read value

       //    }
       //});

       //databaseReference.child("users").child(_email).child("Calendars").child(currentcal).addValueEventListener(new ValueEventListener() {
       //    @Override
       //    public void onDataChange(DataSnapshot dataSnapshot) {
       //        // This method is called once with the initial value and again
       //        // whenever data at this location is updated

       //        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

       //        for (DataSnapshot child: children) {
       //            OurEvent event = (OurEvent) child.getValue();
       //            ourEventArray.add(event);
       //            // arrayList = firebaselist;
       //        }
       //    }

       //    @Override
       //    public void onCancelled(DatabaseError error) {
       //        // Failed to read value

       //    }
       //});
    }

    private void saveEventInfo()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("Calendars").child(currentcal).setValue(ourEventArray);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        Toast.makeText(this, "Calendar Saved...", Toast.LENGTH_SHORT).show();
    }

}
