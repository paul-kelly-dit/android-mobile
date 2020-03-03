package com.example.myapplication;
  
import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;


public class TasksDatabaseHandler extends SQLiteOpenHelper {

    private static String NAME = "name";
    private static String DESCRIPTION = "description";
    private static String STATUS = "status";

    public TasksDatabaseHandler(Context context) {
        super(context, "tasksDb", null, 1);
        //3rd argument to be passed is CursorFactory instance  
    }  
  
    // Creating Tables
    @Override  
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE tasks(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT, " + DESCRIPTION + " TEXT, " + STATUS + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }  
  
    // update database structure
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        // Drop older table if existed  
        db.execSQL("DROP TABLE IF EXISTS tasks");
        // Create tables again
        onCreate(db);  
    }
  
    // add the new task
    void paulWantsToAddATask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();  
  
        ContentValues values = new ContentValues();  
        values.put("name", task.getName()); // Task Name
        values.put("description", task.getDescription()); // Task Phone
        values.put("status", task.getStatus()); // Task Name

        // Inserting Row  
        db.insert("tasks", null, values);
        //2nd argument is String containing nullColumnHack  
        db.close(); // Closing database connection  
    }  
  
    // code to get a single task
        Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tasks", new String[] {"id",
                        "name", "description", "status"}, "id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);  
        if (cursor != null)  
            cursor.moveToFirst();  
  
        Task task = new Task(
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return task  
        return task;  
    }  
  
    // code to get all tasks in a list view  
    public Cursor getAllTasks() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "tasks";
  
        SQLiteDatabase db = this.getWritableDatabase();  
        Cursor taskList = db.rawQuery(selectQuery, null);
  
        return taskList;
    }  
  
    // code to update the single task  
    public int updateTask(Task task) {  
        SQLiteDatabase db = this.getWritableDatabase();  
  
        ContentValues values = new ContentValues();  
        values.put("name", task.getName());
        values.put("description", task.getDescription());
  
        // updating row  
        return db.update("tasks", values, "id" + " = ?",
                new String[] { String.valueOf(task.getID()) });  
    }  
  
    // Deleting single task  
    public void deleteTask(Task task) {  
        SQLiteDatabase db = this.getWritableDatabase();  
        db.delete("tasks", "id" + " = ?",
                new String[] { String.valueOf(task.getID()) });  
        db.close();  
    }  
  
    // Getting tasks Count  
    public int getTasksCount() {  
        String countQuery = "SELECT  * FROM " + "tasks";
        SQLiteDatabase db = this.getReadableDatabase();  
        Cursor cursor = db.rawQuery(countQuery, null);  
        cursor.close();  
  
        // return count  
        return cursor.getCount();  
    }  


}