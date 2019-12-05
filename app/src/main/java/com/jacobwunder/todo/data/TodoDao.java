package com.jacobwunder.todo.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jacobwunder.todo.data.TodoData;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todos")
    List<TodoData> getAll();

    @Query("SELECT * FROM todos WHERE id = :id")
    TodoData getById(int id);

    @Insert
    void insert(TodoData todo);

    @Insert
    void insertAll(TodoData... todos);

    @Update
    void update(TodoData todo);

    @Update
    void updateAll(TodoData... todos);

    @Delete
    void delete(TodoData user);
}
