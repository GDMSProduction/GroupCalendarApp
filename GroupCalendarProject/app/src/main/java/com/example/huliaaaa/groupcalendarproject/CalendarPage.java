package com.example.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class CalendarPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Which Calendars?");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
       // setSupportActionBar(toolbar);
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
                  Intent nextpagge = new Intent(CalendarPage.this,CalendarPage.class);
                  startActivity(nextpagge);
                  return true;
                }
                if (id == R.id.action_Friends)
                {
                    Intent nextpagge = new Intent(CalendarPage.this,FriendsPage.class);
                    startActivity(nextpagge);
                    return true;
                }
                if (id == R.id.action_Themes)
                {
                    Intent nextpagge = new Intent(CalendarPage.this,ThemesPage.class);
                    startActivity(nextpagge);
                    return true;
                }
                if (id == R.id.action_settings)
                {
                    Intent nextpagge = new Intent(CalendarPage.this,SettingsPage.class);
                    startActivity(nextpagge);
                    return true;
                }


                return super.onOptionsItemSelected(item);
            }
    public void NextPage(View V)
    {
        Intent nextpage = new Intent(CalendarPage.this, MyCalendars.class);
        startActivity(nextpage);
    }
    public void NextPage2(View V)
    {
        Intent nextpage = new Intent(CalendarPage.this, GroupCalendars.class);
        startActivity(nextpage);
    }

}
