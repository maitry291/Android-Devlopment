package com.example.cafefrio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private final FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentUser=mAuth.getCurrentUser();
        EditText email=findViewById(R.id.loginEmail);
        EditText password=findViewById(R.id.loginPasswd);
        //getSupportActionBar().hide();
        Button login1 = (Button) findViewById(R.id.login1);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "log in successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this,deshboard.class)); 
                        }
                        else {
                            Toast.makeText(login.this, "Wrong password/email...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                
            }
        });

    }

}