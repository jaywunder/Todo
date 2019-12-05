package com.jacobwunder.todo.ui.todoeditor;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jacobwunder.todo.data.TodoData;

public class TodoEditorViewModel extends ViewModel {
    public static final String TODO_DATA = "TODO_DATA";

    private MutableLiveData<TodoData> todoDataMutableLiveData = new MutableLiveData<>();

    public LiveData<TodoData> getTodoData() {
        return todoDataMutableLiveData;
    }

    public void initialize(TodoData data) {
        todoDataMutableLiveData.postValue(data);
    }
}
