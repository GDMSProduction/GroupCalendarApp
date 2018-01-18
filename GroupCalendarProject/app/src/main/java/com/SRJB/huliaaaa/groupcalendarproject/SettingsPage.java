package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsPage extends AppCompatActivity {


    FirebaseAuth firebaseAuth1;
    private FirebaseAuth.AuthStateListener authStateListener1;
    Button signOut;
   // private static final String TAG = "LoginPage";
    FirebaseUser user;
    String _currenttheme;

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
        setContentView(R.layout.activity_settings_page);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        signOut = (Button) findViewById(R.id.signOutBTN);
        firebaseAuth1 = FirebaseAuth.getInstance();
        authStateListener1 = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 user = firebaseAuth.getCurrentUser();
            }
        };


        signOut.setOnClickListener(new View.OnClickListener()
        {
           @Override
         //   public void onClick(View v) {
         //       if (v.getId() == R.id.signOutBTN) {
         //           firebaseAuth1.getInstance()
         //                   .signOut()
         //                   .addOnCompleteListener(new OnCompleteListener<Void>() {
         //                       public void onComplete(@NonNull Task<Void> task) {
         //                           // user is now signed out
         //                           startActivity(new Intent(SettingsPage.this, LoginPage.class));
         //                           finish();
         //                       }
         //                   });
         //       }
         //   }

            public void onClick(View v)
            {
                firebaseAuth1.getInstance().signOut();
                Toast.makeText(SettingsPage.this, "Signed Out",
                        Toast.LENGTH_SHORT).show();

                Intent j = new Intent(SettingsPage.this, LoginPage.class);
                startActivity(j);
               // Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        });
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
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
            Intent nextpagge = new Intent(SettingsPage.this,MyCalendars.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(SettingsPage.this,FriendsPage.class);
            //startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Themes)
        {
            Intent nextpagge = new Intent(SettingsPage.this,ThemesPage.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_settings)
        {
            Intent nextpagge = new Intent(SettingsPage.this,SettingsPage.class);
            //startActivity(nextpagge);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        if (user == null)
        {
            Intent nextpagge = new Intent(SettingsPage.this,LoginPage.class);
        }
        else
            super.onBackPressed();

    }
}
