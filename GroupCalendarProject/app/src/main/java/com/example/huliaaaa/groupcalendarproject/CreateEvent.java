package com.example.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

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
    CheckBox noDes;
    RadioButton pickTheColor;
    EditText Colorpicked;
    CheckBox redBX;
    CheckBox cyanBX;
    CheckBox greenBX;
    CheckBox magentaBX;
    CheckBox yellowBX;
    int colorPicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
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
        yellowBX= (CheckBox) findViewById(R.id.yellowBox);

        ok1.setVisibility(View.INVISIBLE);
        ok2.setVisibility(View.INVISIBLE);
        Date.setVisibility(View.INVISIBLE);
        Time.setVisibility(View.INVISIBLE);
        Colorpicked.setVisibility(View.INVISIBLE);
        redBX.setVisibility(View.INVISIBLE);
        cyanBX.setVisibility(View.INVISIBLE);
        greenBX.setVisibility(View.INVISIBLE);
        magentaBX.setVisibility(View.INVISIBLE);
        yellowBX.setVisibility(View.INVISIBLE);


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
                yellowBX.setVisibility(View.INVISIBLE);
                noDes.setVisibility(View.INVISIBLE);
                pickTheColor.setVisibility(View.INVISIBLE);
                Time.setVisibility(View.INVISIBLE);

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
                yellowBX.setVisibility(View.INVISIBLE);
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

                        Time.setText(HOUR + ":" + MINUTE);


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
                yellowBX.setVisibility(View.VISIBLE);

                    redBX.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                    Colorpicked.setVisibility(View.VISIBLE);
                    Colorpicked.setText("Red");
                    cyanBX.setChecked(false);
                    greenBX.setChecked(false);
                    magentaBX.setChecked(false);
                    yellowBX.setChecked(false);
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
                    yellowBX.setChecked(false);
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
                    yellowBX.setChecked(false);
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
                    yellowBX.setChecked(false);
                    colorPicked = Color.MAGENTA;

                        }
                    });


                    yellowBX.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                    Colorpicked.setVisibility(View.VISIBLE);
                    Colorpicked.setText("Yellow");
                    cyanBX.setChecked(false);
                    greenBX.setChecked(false);
                    magentaBX.setChecked(false);
                    redBX.setChecked(false);
                    colorPicked = Color.YELLOW;
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
                    }
                    else
                    {
                    Ourevent = new OurEvent(colorPicked, millisecondsS, TITLE.getText().toString(), DESCRIPTION.getText().toString());
                    ourEventArray.add(Ourevent);
                    event = new Event(colorPicked, millisecondsS,TITLE.getText().toString() );
                    }
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
    }


}
