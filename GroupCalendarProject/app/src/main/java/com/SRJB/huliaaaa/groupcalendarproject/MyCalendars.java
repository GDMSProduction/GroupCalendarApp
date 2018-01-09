package com.SRJB.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCalendars extends AppCompatActivity {


    ArrayList<String> myCalendarsList;

    ArrayList<String> arrayList;
    ArrayList<String> firebaselist;
    String privacy;

    EditText editText;
    ArrayAdapter<String> adapter;
    ListView listView;
    Button bt;
    RadioButton rb1;
    RadioButton rb2;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    boolean exists;
    public static String currentcal;

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
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        currentcal = "";

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
                    //databaseReference.child("users").child(user.getUid()).setValue("");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        myCalendarsList = new ArrayList<String>();

        arrayList = new ArrayList<String>();
        firebaselist = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(MyCalendars.this, android.R.layout.simple_list_item_1,
                myCalendarsList);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position,
        //                            long id) {
//
        //        Intent intent = new Intent(MyCalendars.this, CalendarView.class);
        //        startActivity(intent);
        //    }
        //});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentcal = listView.getItemAtPosition(i).toString();
                saveCurrentCalendar();
                Intent intent = new Intent(MyCalendars.this, CalendarView.class);
                startActivity(intent);
            }
        });

        databaseReference.child("users").child(_email).child("Calendars").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    Object title = child.getValue();
                    myCalendarsList.add(title.toString());
                   // arrayList = firebaselist;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



        databaseReference.child("users").child(_email).child("Current Calendar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children) {
                    if (child.getValue().toString().contains(currentcal) && child.getValue().toString() != null)
                    {
                        exists = true;
                        break;
                    }
                    else
                    {
                        exists = false;
                    }

                }
                if(exists == false)
                {
                    exists = false;
                    saveCurrentCalendar();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

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
                     privacy = "Public";
                     saveCalendarInfo();
                     rb2.setChecked(false);
                     editText.setText("");
                 }

                 else if (rb2.isChecked())
                 {
                     String result = editText.getText().toString() + " (Private)";
                     myCalendarsList.add(result);
                     adapter.notifyDataSetChanged();
                     privacy = "Private";
                     saveCalendarInfo();
                     rb1.setChecked(false);
                     editText.setText("");
                 }

                 else
                 {
                     String result = editText.getText().toString();
                     myCalendarsList.add(result);
                     adapter.notifyDataSetChanged();
                     saveCalendarInfo();
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

    private void saveCalendarInfo()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("Calendars").setValue(myCalendarsList);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        Toast.makeText(this, "Calendar Saved...", Toast.LENGTH_SHORT).show();
    }

    private void saveCurrentCalendar()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String _email = user.getEmail();
        String[] parts = _email.split("@");
        _email = parts[0];
        //String title = editText.getText().toString();
        //String p = privacy;

        databaseReference.child("users").child(_email).child("CurrentCal").setValue(currentcal);
        //databaseReference.child("users").child(user.getUid()).child("Calendars").child(title).child("Privacy").setValue(privacy);
        //databaseReference.push();
        //Toast.makeText(this, "Calendar Saved...", Toast.LENGTH_SHORT).show();
    }

}
