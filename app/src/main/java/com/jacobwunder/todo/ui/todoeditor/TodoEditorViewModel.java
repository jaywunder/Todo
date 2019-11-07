package com.jacobwunder.todo.ui.todoeditor;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jacobwunder.todo.data.TodoData;

public class TodoEditorViewModel extends ViewModel {
    public static final String TODO_DATA = "TODO_DATA";

    private MutableLiveData<TodoData> todoDataMutableLiveData;

    public LiveData<TodoData> getTodoData() {
        if (todoDataMutableLiveData == null) {
            todoDataMutableLiveData = new MutableLiveData<>();
        }

        return todoDataMutableLiveData;
    }

    public void initializeFromBundle(Bundle bundle) {
        todoDataMutableLiveData.postValue(
            (TodoData) bundle.getSerializable(TODO_DATA)
        );
    }

    public void saveTodoData() {

    }
}
