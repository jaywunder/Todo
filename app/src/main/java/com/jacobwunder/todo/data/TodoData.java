package com.jacobwunder.todo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import java.util.UUID;

@Entity(tableName = "todos")
public class TodoData implements java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";
    private String description = "";
    private boolean done = false;

    @Ignore
    private String[] tags;

    public TodoData() {}

    public TodoData(String name) {
        this.id = 0;
        this.name = name;
    }

//    public TodoData(int id, String name, String description, String[] tags) {
//        this.id = 0;
//        this.name = name;
//        this.description = description;
//        this.tags = tags;
//    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) { this.tags = tags; }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TodoData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", done=" + done +
                '}';
    }
}
