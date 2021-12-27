package com.example.cafefrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);

                }catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent i= new Intent(splashscreen.this , MainActivity.class);
                    startActivity(i);

                }



            }
        };thread.start();


    }
}