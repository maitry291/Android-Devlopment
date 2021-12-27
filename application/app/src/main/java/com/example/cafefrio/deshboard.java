package com.example.cafefrio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class deshboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);
        getSupportActionBar().hide();
    }
}