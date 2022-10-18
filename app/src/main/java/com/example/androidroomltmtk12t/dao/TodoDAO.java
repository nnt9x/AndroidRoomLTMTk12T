package com.example.androidroomltmtk12t.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroomltmtk12t.model.Todo;

import java.util.List;

@Dao
public interface TodoDAO {

    // Lay ra toan bo
    @Query("SELECT * FROM todos")
    public List<Todo> getAll();

    @Query("SELECT * FROM todos WHERE id=(:id)")
    public Todo getByIfd(long id);

    // Them vao database va tra ve id ban ghi vua tao
    @Insert
    public long create(Todo todo);

    // Update
    @Update
    public void update(Todo todo);

    @Delete
    public void delete(Todo todo);

}
