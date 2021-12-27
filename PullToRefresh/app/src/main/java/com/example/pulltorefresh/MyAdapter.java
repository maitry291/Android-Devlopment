package com.example.pulltorefresh;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<number> {
    Context context;
    int resource ;
    ArrayList<number> list;
    //SwipeRefreshLayout srl;
    static int i=0;

    public MyAdapter(Context context, int resource , ArrayList<number> list){
        super(context, resource ,list);
        this.context=context;
        this.resource=resource;
        this.list=list;
       // this.srl=srl;
    }

    @Nullable
    @Override
    public number getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView= LayoutInflater.from(context).inflate(R.layout.card_view_layout,parent,false);
       try{
           number x=getItem(position);

           TextView t=convertView.findViewById(R.id.text);
           t.setText((CharSequence) x);
       }catch (Exception e){
           System.out.println(e);
       }

      /* srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               srl.setRefreshing(true);

               refresh(position);
           }
       });*/
       return convertView;
    }
   /* private void refresh(int position) {

        srl.setRefreshing(false);

        *//*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(i);
                i++;
                MyAdapter.this.notifyDataSetChanged();
                srl.setRefreshing(false);
            }
        },3000);*//*
    }*/
}
