package com.example.pulltorefresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //SwipeRefreshLayout pullToRefresh = findViewById(R.id.swipeRefreshLayout);
    ListView l=findViewById(R.id.p);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<number> items = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            items.add(new number(i));
        }
        //TextView t=findViewById(R.id.text);

        MyAdapter adapter = new MyAdapter(this, 20, items);

        l.setAdapter(adapter);
    }

}