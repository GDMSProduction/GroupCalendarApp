package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemesPage extends AppCompatActivity {

    boolean exists;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    Button lightheme;
    private int SETTINGS_ACTION = 1;
    Button darktheme;
    Button yellowtheme;
    public static String _currenttheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // SharedPreferences pref = PreferenceManager
        //         .getDefaultSharedPreferences(this);
        // String themeName = pref.getString("theme", "Theme1");
        _currenttheme = MainMenu.currenttheme;




            super.onCreate(savedInstanceState);


        if (_currenttheme.contains("Green")) {
            Toast.makeText(this, "Set theme Green", Toast.LENGTH_SHORT).show();
            setTheme(R.style.TestTheme1);
        } else if (_currenttheme.contains("Purple")) {
            Toast.makeText(this, "Set theme Purple", Toast.LENGTH_SHORT).show();
            setTheme(R.style.PurpDarkBoi);
        }
        else if (_currenttheme.contains("Yellow")) {
            Toast.makeText(this, "Set theme Yellow", Toast.LENGTH_SHORT).show();
            setTheme(R.style.PurpDarkBoi_yellowpeeboi);
        }
            setContentView(R.layout.activity_themes_page);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Theme Settings");
            lightheme = (Button) findViewById(R.id.purpletheme);
            darktheme = (Button) findViewById(R.id.greentheme);
            yellowtheme = (Button) findViewById(R.id.yellowbtn);

            firebaseAuth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            String _email = user.getEmail();
            String[] parts = _email.split("@");
            _email = parts[0];
            String _child = "Current Theme";


           databaseReference.child("users").child(_email).child(_child).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   // This method is called once with the initial value and again
                   // whenever data at this location is updated

                   Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                   for (DataSnapshot child: children) {

                       //_currenttheme = child.getValue().toString();

                   }

               }

               @Override
               public void onCancelled(DatabaseError error) {
                   // Failed to read value

               }
           });




            databaseReference.child("users").child(_email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated

                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for (DataSnapshot child: children) {
                        String email = user.getEmail();
                        String[] parts = email.split("@");
                        email = parts[0];

                        if (email == email)
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
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });



        }

 @Override
 public void onBackPressed()
 {

 }

public void onPurpleClick(View view)
{
    _currenttheme = "Purple";
    saveCurrentTheme();
    Intent intent = new Intent(this, ThemesPage.class);
    startActivity(intent);
    finish();
}

    public void onGreenClick(View view)
    {
        _currenttheme = "Green";
        saveCurrentTheme();
        Intent intent = new Intent(this, ThemesPage.class);
        startActivity(intent);
        finish();
    }
public void onYellowClick(View view)
{
    _currenttheme = "Yellow";
    saveCurrentTheme();
    Intent indendamoondo = new Intent(this, ThemesPage.class);
    startActivity(indendamoondo);
    finish();
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
            Intent nextpagge = new Intent(ThemesPage.this,MyCalendars.class);
            startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Friends)
        {
            Intent nextpagge = new Intent(ThemesPage.this,FriendsPage.class);
            //startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_Themes)
        {
            Intent nextpagge = new Intent(ThemesPage.this,ThemesPage.class);
            //startActivity(nextpagge);
            return true;
        }
        if (id == R.id.action_settings)
        {
            Intent nextpagge = new Intent(ThemesPage.this,SettingsPage.class);
            startActivity(nextpagge);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void saveCurrentTheme()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];

        databaseReference.child("users").child(_email).child("Current Theme").setValue(_currenttheme);
        Toast.makeText(this, "Theme Saved...", Toast.LENGTH_SHORT);
    }
}


