package com.example.huliaaaa.groupcalendarproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginPage extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    FirebaseAuth firebaseAuth;
    private String email;
    private String pass;

    private FirebaseAuth.AuthStateListener authStateListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        txtEmailAddress = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPwd);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth x)
            {

            }
        };


    }
    @Override
    public void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        if (authStateListener != null)
        {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    public void btnUserLogin_Click(View V)
    {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginPage.this, "Please wait....", "Processing....", true);
        (firebaseAuth.signInWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               progressDialog.dismiss();
                                               if (task.isSuccessful())
                                               {
                                                   Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_LONG).show();
                                                   Intent i = new Intent(LoginPage.this, MainMenu.class);
                                                   i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                                   startActivity(i);
                                               }
                                               else
                                               {
                                                   Log.e("ERROR", task.getException().toString());
                                                   Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                               }
                                           }
                                       }
      );
    }

    public void btnRegistrationUser_Click(View V)
    {
        email = txtEmailAddress.getText().toString();
        pass = txtPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(LoginPage.class.getSimpleName(), "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Registration Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginPage.this, "Registration Successful",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
     // final ProgressDialog progressDialog = ProgressDialog.show(LoginPage.this, "Please wait....", "Processing...", true);
     // firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(),
     //         txtPassword.getText().toString()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
     //     @Override
     //     public void onComplete(@NonNull Task<AuthResult> task) {
     //         progressDialog.dismiss();
     //         if (task.isSuccessful())
     //         {
     //             Toast.makeText(LoginPage.this, "Registration successful", Toast.LENGTH_LONG).show();
     //         }
     //         else
     //         {
     //             Log.e("ERROR", task.getException().toString());
     //             Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
     //         }
     //     }
     // });
    }
    public void NextPage(View V)
    {
        Intent nextpage = new Intent(LoginPage.this, MainMenu.class);
                startActivity(nextpage);
    }

}
