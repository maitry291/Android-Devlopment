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

public class Signup<password> extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       // getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        EditText userName=findViewById(R.id.userName);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        Button signIn=findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                   User user=new User(userName.getText().toString(),email.getText().toString(),password.getText().toString());
                                    String id=task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);

                                    Toast.makeText(Signup.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Signup.this,deshboard.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(Signup.this, "Not successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}