package com.example.huliaaaa.groupcalendarproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.example.huliaaaa.groupcalendarproject.OurEvent;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

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


    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    Date fat;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("October - 2017");

        ourEventsArray = new ArrayList<OurEvent>();
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        title = (EditText) findViewById(R.id.titleTXT);
        d = (EditText) findViewById(R.id.dateTXT);
        description = (EditText) findViewById(R.id.descriptionTXT);
        b2 = (Button) findViewById(R.id.createEvent);
        b3 = (Button) findViewById(R.id.addBTN);
        listView1 = (ListView) findViewById(R.id.eventList);
        eventDes = (EditText) findViewById(R.id.descriptionBox);
        eventDT = (EditText) findViewById(R.id.datetimeBox);
        eventTitle = (TextView) findViewById(R.id.titlebox);
        editBtn = (Button) findViewById(R.id.editBtn);
        tv1 = (TextView) findViewById(R.id.textView10);
        tv2 = (TextView) findViewById(R.id.textView14);
        ex1 = (TextView) findViewById(R.id.dateExample);
        ex2 = (TextView) findViewById(R.id.dateExample2);
        exit = (Button) findViewById(R.id.exit);



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

                for (int j = 0; j < ourEventsArray.size(); ++j)
                {
                     data = ourEventsArray.get(j).getData().toString();
                     oe = ourEventsArray.get(j);
                    for (int k =0; k < arrayList1.size(); ++k)
                    {
                        other = arrayList1.get(k);
                        if (data.equals(other))
                        {
                            nd = new Date(oe.getTimeInMillis());
                           DateFormat  df = new SimpleDateFormat("MM/dd/yy    HH:mm");

                            //formatted value of current Date
                            df.format(nd);



                            compactCalendar.setVisibility(View.INVISIBLE);
                            b2.setVisibility(View.INVISIBLE);
                            editBtn.setVisibility(View.VISIBLE);
                            eventTitle.setVisibility(View.VISIBLE);
                            eventDT.setVisibility(View.VISIBLE);
                            eventDes.setVisibility(View.VISIBLE);
                            tv1.setVisibility(View.VISIBLE);
                            tv2.setVisibility(View.VISIBLE);
                            exit.setVisibility(View.VISIBLE);
                            listView1.setVisibility(View.INVISIBLE);
                            eventTitle.setText(oe.getData().toString());
                            eventDes.setText(oe.getDescription().toString());
                            eventDT.setText(df.format(nd));




                        }
                }


                }
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

                 // if (dateClicked.getDate() == events[i].getTimeInMillis() && events[i] != null) {
                 //     Toast.makeText(context, events[i].getData().toString(), Toast.LENGTH_SHORT).show();
                 // }else {

                 //     Toast.makeText(context, "lol", Toast.LENGTH_SHORT).show();
                 // }
                   // d.setText(dateClicked.toString());

               // d.requestFocus();



            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
        onBtnClick2();
        onBtnClick3();
        onExitClick();
    }

    public void onBtnClick2()
    {
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                description.setVisibility(View.VISIBLE);
                d.setVisibility(View.VISIBLE);
                listView1.setVisibility(View.INVISIBLE);

                title.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);

                ex1.setVisibility(View.VISIBLE);
                ex2.setVisibility(View.VISIBLE);

                title.requestFocus();


            }
        });
    }
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
    public void onBtnClick3()
    {
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String des = description.getText().toString();
               // View focusView = null;
                String tit = title.getText().toString();
                String dat = d.getText().toString();


                if (TextUtils.isEmpty(des))
                {
                    description.setError(getString(R.string.error_field_required));

                }
                else if (TextUtils.isEmpty(tit))
                {
                    title.setError(getString(R.string.error_field_required));
                }
                else if (TextUtils.isEmpty(dat))
                {
                    d.setError(getString(R.string.error_field_required));
                }
                else
                {
                    String q = d.getText().toString();
                    Date date = new Date(q);
                    long milliseconds = date.getTime();



                    ourEvent = new OurEvent(Color.RED, milliseconds, title.getText().toString(), description.getText().toString());
                    ourEventsArray.add(ourEvent);
                    Event event = new Event(Color.RED, milliseconds,title.getText().toString() );
                    compactCalendar.addEvent(event);
                    description.setVisibility(View.INVISIBLE);
                    d.setVisibility(View.INVISIBLE);
                    title.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    ex1.setVisibility(View.INVISIBLE);
                    ex2.setVisibility(View.INVISIBLE);


                    description.setText("");
                    d.setText("");
                    title.setText("");
                    compactCalendar.setCurrentDate(p);
                }





            }
        });
    }

}