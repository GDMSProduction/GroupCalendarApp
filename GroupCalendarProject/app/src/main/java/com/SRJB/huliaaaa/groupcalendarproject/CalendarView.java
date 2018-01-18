package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarView extends AppCompatActivity
{
    CompactCalendarView compactCalendar;
    EditText title;
    EditText d;
    EditText t;
    EditText description;
    Button b2;
    Button b3;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> adapter1;
    ListView listView1;
    ArrayList<OurEvent> ourEventsArray;
    OurEvent ourEvent = new OurEvent(Color.RED, 1433701251000L, "cool", "wee");
    TextView eventTitle;
    EditText eventDes;
    EditText eventDT;
    Button editBtn;
    List<Event> events;
    String data;
    String other;
    OurEvent oe;
    TextView tv1;
    TextView tv2;
    Date nd;
    TextView ex1;
    TextView ex2;
    Button exit;
    long m = System.currentTimeMillis();
    Date p = new Date(m);
    Button delete;
    CheckBox ok;
    CheckBox no;
    TextView ays;
    RadioButton pickDate;
    RadioButton pickTime;
    TimePicker tp;
    DatePicker dp;
    Button okay;
    Button okay2;
    String thedate;
    EditText time;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    boolean exists;
    FirebaseAuth firebaseAuth;
    String currentcal = MyCalendars.currentcal;
    public static String currenteventdata;
    //String calendartouse;

    int _color;
    long millis;
    Object _title;
    Event NewEvent;

    ArrayList<Integer> colors;
    ArrayList<Object> datas;
    ArrayList<Long> timesinmillis;
    String _currenttheme;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    Date fat;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _currenttheme = MainMenu.currenttheme;
        if (_currenttheme.contains("Dark")) {

            setTheme(R.style.TestTheme1);
        } else if (_currenttheme.contains("Light")) {

            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_calendar_view);
        colors = new ArrayList<Integer>();
        datas = new ArrayList<Object>();
        timesinmillis = new ArrayList<Long>();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month ==1 )
        actionBar.setTitle("January" + " " + year);
        else if (month == 2)
            actionBar.setTitle("February" + " " + year);
        else if (month == 3)
            actionBar.setTitle("March" + " " + year);
        else if (month == 4)
            actionBar.setTitle("April" + " " + year);
        else if (month == 5)
            actionBar.setTitle("May" + " " + year);
        else if (month == 6)
            actionBar.setTitle("June" + " " + year);
        else if (month == 7)
            actionBar.setTitle("July" + " " + year);
        else if (month == 8)
            actionBar.setTitle("August" + " " + year);
        else if (month == 9)
            actionBar.setTitle("September" + " " + year);
        else if (month == 10)
            actionBar.setTitle("October" + " " + year);
        else if (month == 11)
            actionBar.setTitle("November" + " " + year);
        else if (month == 12)
            actionBar.setTitle("December" + " " + year);


        ourEventsArray = new ArrayList<OurEvent>();
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
       // title = (EditText) findViewById(R.id.titleTXT);
       // d = (EditText) findViewById(R.id.dateTXT);
      //  description = (EditText) findViewById(R.id.descriptionTXT);
        b2 = (Button) findViewById(R.id.createEvent);
      //  b3 = (Button) findViewById(R.id.addBTN);
        listView1 = (ListView) findViewById(R.id.eventList);
        eventDes = (EditText) findViewById(R.id.descriptionBox);
        eventDT = (EditText) findViewById(R.id.datetimeBox);
        eventTitle = (TextView) findViewById(R.id.titlebox);
        editBtn = (Button) findViewById(R.id.editBtn);
        //tv1 = (TextView) findViewById(R.id.textView10);
        tv2 = (TextView) findViewById(R.id.textView14);


        exit = (Button) findViewById(R.id.exit);
        delete = (Button) findViewById(R.id.deleteBTN);
        ok = (CheckBox) findViewById(R.id.yesBTN);
        no = (CheckBox) findViewById(R.id.noBtn);
        ays = (TextView) findViewById(R.id.confirmTXT);
      //  pickDate = (RadioButton) findViewById(R.id.pickDateBTN);
      //  pickTime = (RadioButton) findViewById(R.id.pickTimeBTN);
      //  tp = (TimePicker) findViewById(R.id.timePicker);
        dp = (DatePicker) findViewById(R.id.datePicker);
      //  okay = (Button) findViewById(R.id.okBTN);
      //  okay2 = (Button) findViewById(R.id.ok2BTN);
      //  time = (EditText) findViewById(R.id.timeTXT);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];

        //calendartouse = "hi";
        _color = 0;


        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(CalendarView.this, android.R.layout.simple_list_item_1,
                arrayList1);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id)
            {
                currenteventdata = listView1.getItemAtPosition(position).toString();
                saveCurrentEvent();
                Intent intendo= new Intent(CalendarView.this, EditEvent.class);
                startActivity(intendo);
           // for (int j = 0; j < ourEventsArray.size(); ++j)
           // {
           //      data = ourEventsArray.get(j).getData().toString();
           //      oe = ourEventsArray.get(j);
           //     for (int k =0; k < arrayList1.size(); ++k)
           //     {
           //         other = arrayList1.get(k);
           //         if (data.equals(other))
           //         {
           //             nd = new Date(oe.getTimeInMillis());
           //            DateFormat  df = new SimpleDateFormat("MM/dd/yy    HH:mm");

           //             //formatted value of current Date
           //             df.format(nd);



           //             compactCalendar.setVisibility(View.INVISIBLE);
           //             b2.setVisibility(View.INVISIBLE);
           //             editBtn.setVisibility(View.VISIBLE);
           //             eventTitle.setVisibility(View.VISIBLE);
           //             eventDT.setVisibility(View.VISIBLE);
           //             eventDes.setVisibility(View.VISIBLE);
           //             tv1.setVisibility(View.VISIBLE);
           //             tv2.setVisibility(View.VISIBLE);
           //             exit.setVisibility(View.VISIBLE);
           //             listView1.setVisibility(View.INVISIBLE);
           //             eventTitle.setText(oe.getData().toString());
           //             eventDes.setText(oe.getDescription().toString());
           //             eventDT.setText(df.format(nd));
           //             delete.setVisibility(View.VISIBLE);

           //             delete.setOnClickListener(new View.OnClickListener() {
           //                 @Override
           //                 public void onClick(View v) {
           //                     ok.setVisibility(View.VISIBLE);
           //                     ays.setVisibility(View.VISIBLE);
           //                     no.setVisibility(View.VISIBLE);
           //                     editBtn.setVisibility(View.INVISIBLE);
           //                     eventTitle.setVisibility(View.INVISIBLE);
           //                     eventDT.setVisibility(View.INVISIBLE);
           //                     eventDes.setVisibility(View.INVISIBLE);
           //                     tv1.setVisibility(View.INVISIBLE);
           //                     tv2.setVisibility(View.INVISIBLE);
           //                     exit.setVisibility(View.INVISIBLE);

           //                         ok.setOnClickListener(new View.OnClickListener() {
           //                             @Override
           //                             public void onClick(View v) {

           //                         compactCalendar.setVisibility(View.VISIBLE);
           //                         b2.setVisibility(View.VISIBLE);
           //                         compactCalendar.removeEvents(oe.getTimeInMillis());
           //                                 ok.setVisibility(View.INVISIBLE);
           //                                 ays.setVisibility(View.INVISIBLE);
           //                                 no.setVisibility(View.INVISIBLE);
           //                                 ok.setChecked(false);
           //                                 delete.setVisibility(View.INVISIBLE);
           //                             }
           //                         });



           //                         no.setOnClickListener(new View.OnClickListener() {
           //                             @Override
           //                             public void onClick(View v) {

           //                         ok.setVisibility(View.INVISIBLE);
           //                         ays.setVisibility(View.INVISIBLE);
           //                         no.setVisibility(View.INVISIBLE);
           //                         editBtn.setVisibility(View.VISIBLE);
           //                         eventTitle.setVisibility(View.VISIBLE);
           //                         eventDT.setVisibility(View.VISIBLE);
           //                         eventDes.setVisibility(View.VISIBLE);
           //                         tv1.setVisibility(View.VISIBLE);
           //                         tv2.setVisibility(View.VISIBLE);
           //                         exit.setVisibility(View.VISIBLE);
           //                                 delete.setVisibility(View.INVISIBLE);
           //                                 no.setChecked(false);
           //                             }
           //                         });


           //                 }
           //             });



           //         }
           // }


           // }


            }
        });




        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener()
        {

            @Override
            public void onDayClick(Date dateClicked)
            {
                arrayList1.clear();
                Context context = getApplicationContext();
                events = compactCalendar.getEvents(dateClicked);
                if (events.size() > 0)
                {
                    for (int i = 0; i < events.size(); ++i)
                    {
                       // Toast.makeText(context, events.get(i).getData().toString(), Toast.LENGTH_LONG).show();
                        arrayList1.add(events.get(i).getData().toString());
                        adapter1.notifyDataSetChanged();
                    }
                    listView1.setVisibility(View.VISIBLE);

                }
                else
                {
                    arrayList1.clear();
                    adapter1.notifyDataSetChanged();
                }




            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        databaseReference.child("users").child(_email).child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    if (child.getKey().toString().contains(currentcal))
                    {
                     //   calendartouse = child.getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

       databaseReference.child("users").child(_email).child("Events").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               // This method is called once with the initial value and again
               // whenever data at this location is updated
               colors.clear();
               timesinmillis.clear();
               datas.clear();
               Iterable<DataSnapshot> children = dataSnapshot.getChildren();

               for (DataSnapshot child: children) {
                   Iterable<DataSnapshot> children2 = child.getChildren();
                   if (child.getKey().toString().contains(currentcal)) {


                       for (DataSnapshot child2 : children2) {


                           Iterable<DataSnapshot> children3 = child2.getChildren();
                           for (DataSnapshot child3 : children3) {
                               if (child3.getKey().toString().contains("color") && child3.exists()) {
                                   colors.add(child3.getValue(Integer.class));
                               } else if (child3.getKey().toString().contains("data")) {
                                   datas.add(child3.getValue());
                               } else if (child3.getKey().toString().contains("timeInMillis")) {
                                   timesinmillis.add(child3.getValue(Long.class));
                               }
                           }

                           compactCalendar.removeAllEvents();

                           for (int x=0; x<colors.size(); x++) {
                               NewEvent = new Event(colors.get(x), timesinmillis.get(x), datas.get(x));
                               compactCalendar.addEvent(NewEvent);
                           }
                       }

                   }


               }


           }

           @Override
           public void onCancelled(DatabaseError error) {
               // Failed to read value

           }
       });



        //onBtnClick2();
     //   onBtnClick3();
        onExitClick();
        CreateEventClick();
    }
    public void onDeleteClick()
    {
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    public void CreateEventClick()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten= new Intent(CalendarView.this, CreateEvent.class);
                startActivity(inten);
            }
        });
    }
// public void onBtnClick2()
// {
//     b2.setOnClickListener(new View.OnClickListener()
//     {
//         @Override
//         public void onClick(View v)
//         {
//             description.setVisibility(View.VISIBLE);
//            // d.setVisibility(View.VISIBLE);
//             listView1.setVisibility(View.INVISIBLE);
//             b2.setVisibility(View.INVISIBLE);

//             title.setVisibility(View.VISIBLE);
//             b3.setVisibility(View.VISIBLE);

//            // ex1.setVisibility(View.VISIBLE);
//            // ex2.setVisibility(View.VISIBLE);
//             pickDate.setVisibility(View.VISIBLE);
//             pickTime.setVisibility(View.VISIBLE);

//             pickDate.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     pickDate.setChecked(true);
//                     dp.setVisibility(View.VISIBLE);
//                     description.setVisibility(View.INVISIBLE);
//                     //d.setVisibility(View.INVISIBLE);
//                     listView1.setVisibility(View.INVISIBLE);
//                     b2.setVisibility(View.INVISIBLE);

//                     title.setVisibility(View.INVISIBLE);
//                     b3.setVisibility(View.INVISIBLE);


//                     pickDate.setVisibility(View.INVISIBLE);
//                     pickTime.setVisibility(View.INVISIBLE);
//                     compactCalendar.setVisibility(View.INVISIBLE);
//                     okay.setVisibility(View.VISIBLE);

//                     okay.setOnClickListener(new View.OnClickListener() {
//                         @Override
//                         public void onClick(View v) {
//                             dp.setVisibility(View.INVISIBLE);
//                             description.setVisibility(View.VISIBLE);
//                           //  d.setVisibility(View.VISIBLE);
//                             listView1.setVisibility(View.VISIBLE);
//                             b2.setVisibility(View.VISIBLE);

//                             title.setVisibility(View.VISIBLE);
//                             b3.setVisibility(View.VISIBLE);

//                             //ex1.setVisibility(View.VISIBLE);
//                             //ex2.setVisibility(View.VISIBLE);
//                             pickDate.setVisibility(View.VISIBLE);
//                             pickTime.setVisibility(View.VISIBLE);
//                             compactCalendar.setVisibility(View.VISIBLE);
//                             okay.setVisibility(View.INVISIBLE);
//                           //  dp.setMinDate(System.currentTimeMillis());

//                           int z = dp.getDayOfMonth();
//                           int za = dp.getMonth();
//                           int zq = dp.getYear();
//                          String zz = String.valueOf(z);
//                          String zaa =String.valueOf(za);
//                          String zqq =String.valueOf(zq);
//                          thedate = zaa+ "/"  + zz+ "/" + zqq;
//                          //   d.setText(thedate);



//                            // Date date = new Date(q);
//                             //long milliseconds = z+za+zq;


//                        // nd = new Date(dp.getYear(),
//                        // dp.getMonth(),
//                        // dp.getDayOfMonth());

//                        // //doesnt actually format
//                        // DateFormat fd = new SimpleDateFormat("MM/dd/yy");


//                        // //formatted value of current Date
//                        // fd.format(nd);
//                        // d.setText(nd.toString());

//                         }
//                     });


//                 }
//             });
//             pickTime.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     pickTime.setChecked(true);
//                     tp.setVisibility(View.VISIBLE);
//                     description.setVisibility(View.INVISIBLE);
//                    // d.setVisibility(View.INVISIBLE);
//                     listView1.setVisibility(View.INVISIBLE);
//                     b2.setVisibility(View.INVISIBLE);

//                     title.setVisibility(View.INVISIBLE);
//                     b3.setVisibility(View.INVISIBLE);

//                     pickDate.setVisibility(View.INVISIBLE);
//                     pickTime.setVisibility(View.INVISIBLE);
//                     compactCalendar.setVisibility(View.INVISIBLE);
//                     okay2.setVisibility(View.VISIBLE);

//                     okay2.setOnClickListener(new View.OnClickListener() {
//                         @Override
//                         public void onClick(View v) {
//                             tp.setVisibility(View.INVISIBLE);
//                             description.setVisibility(View.VISIBLE);
//                             //d.setVisibility(View.VISIBLE);
//                             listView1.setVisibility(View.VISIBLE);
//                             b2.setVisibility(View.VISIBLE);
//                            // time.setVisibility(View.VISIBLE);

//                             title.setVisibility(View.VISIBLE);
//                             b3.setVisibility(View.VISIBLE);


//                             pickDate.setVisibility(View.VISIBLE);
//                             pickTime.setVisibility(View.VISIBLE);
//                             compactCalendar.setVisibility(View.VISIBLE);
//                             okay2.setVisibility(View.INVISIBLE);

//                             int hour = tp.getHour();
//                             int minute =  tp.getMinute();

//                             String thehour = String.valueOf(hour);
//                             String theminute = String.valueOf(minute);


//                           //  time.setText(thehour + ":" + theminute);

//                         }
//                     });
//                 }
//             });


//             title.requestFocus();


//         }
//     });
// }
public void onExitClick()
{
    exit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            compactCalendar.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            editBtn.setVisibility(View.INVISIBLE);
            eventTitle.setVisibility(View.INVISIBLE);
            eventDT.setVisibility(View.INVISIBLE);
            eventDes.setVisibility(View.INVISIBLE);
            tv1.setVisibility(View.INVISIBLE);
            tv2.setVisibility(View.INVISIBLE);
            exit.setVisibility(View.INVISIBLE);
            listView1.setVisibility(View.INVISIBLE);
            compactCalendar.setCurrentDate(p);


        }
    });
}
 // public void onBtnClick3()
 // {
 //     b3.setOnClickListener(new View.OnClickListener()
 //     {
 //         @Override
 //         public void onClick(View v)
 //         {


 //             String des = description.getText().toString();
 //            // View focusView = null;
 //             String tit = title.getText().toString();
 //           //  String dat = d.getText().toString();


 //             if (TextUtils.isEmpty(des))
 //             {
 //                 description.setError(getString(R.string.error_field_required));

 //             }
 //             else if (TextUtils.isEmpty(tit))
 //             {
 //                 title.setError(getString(R.string.error_field_required));
 //             }
 //          // else if (TextUtils.isEmpty(dat))
 //          // {
 //          //     d.setError(getString(R.string.error_field_required));
 //          // }
 //             else
 //             {
 //            // int z = dp.getDayOfMonth();
 //            // int za = dp.getMonth();
 //            // int zq = dp.getYear();
 //            // String zz = Integer.toString(z);
 //            // String zaa = Integer.toString(za);
 //            // String zqq = Integer.toString(zq);
 //            // String q = zz + zaa + zqq;
 //            // Date date = new Date(q);
 //            // long milliseconds = date.getTime();
 //                 Date date = new Date(dp.getYear()-1900, dp.getMonth(), dp.getDayOfMonth(), tp.getHour(), tp.getMinute());
 //                 long milliseconds = date.getTime();



 //                 ourEvent = new OurEvent(Color.RED, milliseconds, title.getText().toString(), description.getText().toString());
 //                 ourEventsArray.add(ourEvent);
 //                 Event event = new Event(Color.RED, milliseconds,title.getText().toString() );
 //                 compactCalendar.addEvent(event);
 //                 description.setVisibility(View.INVISIBLE);
 //                // d.setVisibility(View.INVISIBLE);
 //                 title.setVisibility(View.INVISIBLE);
 //                 b3.setVisibility(View.INVISIBLE);
 //                 pickDate.setVisibility(View.INVISIBLE);
 //                 pickTime.setVisibility(View.INVISIBLE);



 //                 description.setText("");
 //               //  d.setText("");
 //                 title.setText("");
 //                 compactCalendar.setCurrentDate(p);
 //             }





 //         }
 //     });
 // }

    private void saveCurrentEvent()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("CurrentEvent").setValue(currenteventdata);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        //Toast.makeText(this, "Calendar Saved...", Toast.LENGTH_SHORT).show();
    }

}