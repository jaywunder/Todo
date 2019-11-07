package com.jacobwunder.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jacobwunder.todo.ui.todoeditor.TodoEditorFragment;
import com.jacobwunder.todo.ui.todoeditor.TodoEditorViewModel;

import java.io.Serializable;

public class TodoEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_editor_activity);

        Intent intent = getIntent();
        Serializable todoData = intent.getSerializableExtra(TodoEditorViewModel.TODO_DATA);

        TodoEditorFragment frag = TodoEditorFragment.newInstance();
        Bundle args = new Bundle();
        args.putSerializable(TodoEditorViewModel.TODO_DATA, todoData);
        frag.setArguments(args);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.todo_editor_activity_container, frag)
                    .commitNow();
        }
    }
}
