package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends Activity {

    TextView tasks;
    List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_main);


        TasksDatabaseHandler db = new TasksDatabaseHandler(this);

        // Add some sample Persons
        Log.d("Insert: ", "Inserting ..");
        db.paulWantsToAddATask(new Task("Shopping", "Get the weekly shop","0"));
        db.paulWantsToAddATask(new Task("hair", "Get a hair cut","1"));
        db.paulWantsToAddATask(new Task("study", "CAs to be done","0"));


        // Retrieve all persons
        Log.d("Reading: ", "Reading all persons..");


        Cursor cursor= db.getAllTasks();

        taskList = new ArrayList<Task>();
        tasks = (TextView)findViewById(R.id.tasks);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task= new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setStatus(cursor.getString(3));
                // Adding task to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        StringBuffer taskText = new StringBuffer();
        for (Task task : taskList) {
            // For reference, add to the logcat to make what's going on more visible
            Log.d("Name: ", task.getName());
            taskText.append("Id: " + task.getID() + " ,Name: " + task.getName() + " ,Description: " +
                    task.getDescription() + " , status: " +
                    task.getStatus() + "\n");
        }

        tasks.setText(taskText);
    }
}