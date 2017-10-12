package com.example.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

public class MainMenu extends AppCompatActivity {

    private TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
      // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
      //  setSupportActionBar(toolbar);
        //tvEmail = (TextView) findViewById(R.id.txtEmail);
       // tvEmail.setText(getIntent().getExtras().getString("Email"));
    }


    public boolean onCreateOptionsMenu(Menu m)
    {
//        MenuInflater mf = getMenuInflater();
//        mf.inflate(R.menu.toolbar_menu, m);
//        return true;
        getMenuInflater().inflate(R.menu.toolbar_menu,m);

        return super.onCreateOptionsMenu(m);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Calendars) {
            Intent nextpagge = new Intent(MainMenu.this,CalendarPage.class);
            startActivity(nextpagge);

        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(MainMenu.this,FriendsPage.class);
            startActivity(nextpagge);

        }
        if (id == R.id.action_Themes)
        {
            Intent nextpagge = new Intent(MainMenu.this,ThemesPage.class);
            startActivity(nextpagge);
            //return true;
        }
        if (id == R.id.action_settings)
        {
            Intent nextpagge = new Intent(MainMenu.this,SettingsPage.class);
            startActivity(nextpagge);
           // return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void NextPage(View V)
    {
        Intent nextpage = new Intent(MainMenu.this, CalendarPage.class);
        startActivity(nextpage);
    }
    public void NextPageFriends(View V)
    {
        Intent nextpage = new Intent(MainMenu.this, FriendsPage.class);
        startActivity(nextpage);
    }
    public void NextPageThemes(View V)
    {
        Intent nextpage = new Intent(MainMenu.this, ThemesPage.class);
        startActivity(nextpage);
    }
    public void NextPageSetttings(View V)
    {
        Intent nextpage = new Intent(MainMenu.this, SettingsPage.class);
        startActivity(nextpage);
    }
}
