package com.jacobwunder.todo.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TodoData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

}
