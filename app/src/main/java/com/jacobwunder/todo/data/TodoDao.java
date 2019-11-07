package com.jacobwunder.todo.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.jacobwunder.todo.data.TodoData;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todos")
    List<TodoData> getAll();

    @Query("SELECT * FROM todos WHERE id = :id")
    TodoData getById(int id);

    @Insert
    void insertAll(TodoData... todos);

    @Delete
    void delete(TodoData user);
}
