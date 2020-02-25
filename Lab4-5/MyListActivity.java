package com.example.paulkelly.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyListActivity extends ListActivity {

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String itemAtPosition = (String)l.getItemAtPosition(position);

        String paul = "Paul";

        Log.i("INFO", "Paul Size : " + paul.length());
        Log.i("INFO", "\n\n\n>>>> >>>>  User clicked " + itemAtPosition + "\n\n");

        Toast.makeText(getApplicationContext(), itemAtPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paulslist);
        ArrayList<String> paulsData = new ArrayList<String>();
        paulsData.add("One");
        paulsData.add("Two");
        paulsData.add("Three");
        paulsData.add("Four");
        paulsData.add("Five");

//        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paulsData));
        setListAdapter(new ArrayAdapter(this, R.layout.row, R.id.month_paul, paulsData));

    }

}
