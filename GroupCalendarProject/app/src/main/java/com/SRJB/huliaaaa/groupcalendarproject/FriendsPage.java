package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FriendsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_page);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Friends");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        //setSupportActionBar(toolbar);
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
            Intent nextpagge = new Intent(FriendsPage.this,CalendarPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(FriendsPage.this,FriendsPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Themes)
        {
            Intent nextpagge = new Intent(FriendsPage.this,ThemesPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_settings)
        {
            Intent nextpagge = new Intent(FriendsPage.this,SettingsPage.class);
            startActivity(nextpagge);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
