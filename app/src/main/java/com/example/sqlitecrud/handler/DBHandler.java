package com.example.sqlitecrud.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlitecrud.model.Task;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DbName = "MyDB";
    public DBHandler(Context context){
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean Insert_Record(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("name", task.getTaskName());
        c.put("description", task.getTaskDesc());

        long result = db.insert("tasks", null, c);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> Read_Records(){
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM tasks";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            String row = cursor.getString(0) + "\t\t\t" + cursor.getString(1) + "\t\t\t" + cursor.getString(2);
            data.add(row);
        }
        return data;
    }

    public boolean Update_Record(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("name", task.getTaskName());
        c.put("description", task.getTaskDesc());

        int result = db.update("tasks", c, "id=?", new String[]{String.valueOf(task.getId())});
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean  Delete_Record(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete("tasks", "id=?", new String[]{id});
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
}

