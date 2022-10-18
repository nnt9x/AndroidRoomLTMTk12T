package com.example.androidroomltmtk12t.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidroomltmtk12t.dao.TodoDAO;
import com.example.androidroomltmtk12t.model.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDAO getTodoDAO();
}
