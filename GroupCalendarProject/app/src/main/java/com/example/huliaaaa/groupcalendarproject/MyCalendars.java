package com.example.huliaaaa.groupcalendarproject;

import android.app.LauncherActivity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MyCalendars extends AppCompatActivity {

    ArrayList<String> myCalendarsList;
    EditText editText;
    ArrayAdapter<String> adapter;
    ListView listView;
    Button bt;
    RadioButton rb1;
    RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendars);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Calendars");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        //setSupportActionBar(toolbar);listView = (ListView) findViewById(R.id.listView);

        editText = (EditText) findViewById(R.id.calendartitle);
        listView = (ListView) findViewById(R.id.calendarTitleList);
        bt = (Button) findViewById(R.id.createCalendarBTN);
        rb1 = (RadioButton) findViewById(R.id.publicBTN);
        rb2 = (RadioButton) findViewById(R.id.privateBTN);
        rb1.setChecked(true);


        myCalendarsList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MyCalendars.this, android.R.layout.simple_list_item_1,
                myCalendarsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MyCalendars.this, CalendarView.class);
                startActivity(intent);
            }
        });

        onBtnClick();
        clicka();
        clicka2();
    }
     public void onBtnClick(){
         bt.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 String titl = editText.getText().toString();

                 if (TextUtils.isEmpty(titl))
                 {
                     editText.setError("Field Required");
                 }
                 else if (rb1.isChecked())
                 {
                     String result = editText.getText().toString() + " (Public)";
                     myCalendarsList.add(result);
                     adapter.notifyDataSetChanged();
                     rb2.setChecked(false);
                     editText.setText("");
                 }

                 else if (rb2.isChecked())
                 {
                     String result = editText.getText().toString() + " (Private)";
                     myCalendarsList.add(result);
                     adapter.notifyDataSetChanged();
                     rb1.setChecked(false);
                     editText.setText("");
                 }

                 else
                 {
                     String result = editText.getText().toString();
                     myCalendarsList.add(result);
                     adapter.notifyDataSetChanged();
                     editText.setText("");
                     editText.setError(null);

                 }
                 rb1.setChecked(true);
                 rb2.setChecked(false);


             }
         });
     }
public void clicka()
{
    rb2.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            rb1.setChecked(false);
        }
    });


}
    public void clicka2()
    {
        rb1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                rb2.setChecked(false);
            }
        });


    }


    public boolean onCreateOptionsMenu(Menu m)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,m);

        return super.onCreateOptionsMenu(m);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Calendars) {
            Intent nextpagge = new Intent(MyCalendars.this,CalendarPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(MyCalendars.this,FriendsPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Themes)
        {
            Intent nextpagge = new Intent(MyCalendars.this,ThemesPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_settings)
        {
            Intent nextpagge = new Intent(MyCalendars.this,SettingsPage.class);
            startActivity(nextpagge);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
