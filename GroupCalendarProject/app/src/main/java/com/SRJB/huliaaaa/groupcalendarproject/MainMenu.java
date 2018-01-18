package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;

import android.support.v7.app.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView tvEmail;
    Menu damenu;
    public static String currenttheme;
    boolean exists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        //saveUserInfo();

        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        final String _child = "Current Theme";
        currenttheme = "";

        databaseReference.child("users").child(_email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    if (child.getKey().contains(_child))
                    {
                        currenttheme = child.getValue().toString();
                        if (currenttheme.contains("Dark")) {
                            setTheme(R.style.TestTheme1);

                        } else if (currenttheme.contains("Light")) {
                            setTheme(R.style.AppTheme);
                        }

                        setContentView(R.layout.activity_main_menu);
                        final ActionBar actionBar = getSupportActionBar();
                        actionBar.setTitle("Main Menu");

                    }
                    else
                    {
                        setContentView(R.layout.activity_main_menu);
                        final ActionBar actionBar = getSupportActionBar();
                        actionBar.setTitle("Main Menu");
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
      // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);

      //  setSupportActionBar(toolbar);
        //tvEmail = (TextView) findViewById(R.id.txtEmail);
       // tvEmail.setText(getIntent().getExtras().getString("Email"));
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    String email = user.getEmail();
                    String[] parts = email.split("@");
                    email = parts[0];
                    if (child.getKey().toString().contains(email))
                    {
                        exists = true;
                        break;
                    }
                    else
                    {

                    }

                }
                if(exists == false)
                {
                    exists = false;
                    saveUserInfo();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }



    public boolean onCreateOptionsMenu(Menu m)
    {
//        MenuInflater mf = getMenuInflater();
//        mf.inflate(R.menu.toolbar_menu, m);
//        return true;
        getMenuInflater().inflate(R.menu.toolbar_menu,m);

      // m.findItem(R.menu.toolbar_menu).setTitle("Main Menu");


        return super.onCreateOptionsMenu(m);
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Calendars) {
            Intent nextpagge = new Intent(MainMenu.this,MyCalendars.class);
            startActivity(nextpagge);

        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(MainMenu.this,FriendsPage.class);
            //startActivity(nextpagge);

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
        Intent nextpage = new Intent(MainMenu.this, MyCalendars.class);
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

    private void saveUserInfo()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        String[] parts = email.split("@");
        email = parts[0];
        //Random rand = new Random(SystemClock.elapsedRealtime());
        //int index = rand.nextInt(1000000000);
        //String name = "User" + index;
        databaseReference.child("users").child(email).setValue(user.getUid());
        //databaseReference.push();
        //Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
    }
}
