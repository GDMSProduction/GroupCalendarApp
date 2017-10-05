package com.example.huliaaaa.groupcalendarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void NextPage(View V)
    {
        Intent nextpage = new Intent(LoginPage.this, MainMenu.class);
                startActivity(nextpage);
    }
}
