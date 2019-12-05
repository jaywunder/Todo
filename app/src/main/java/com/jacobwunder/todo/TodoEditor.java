package com.jacobwunder.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.jacobwunder.todo.data.TodoData;
import com.jacobwunder.todo.ui.todoeditor.TodoEditorViewModel;

import java.io.Serializable;

public class TodoEditor extends AppCompatActivity {

    private TodoEditorViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_editor_activity);

        TodoData todoData = (TodoData) getIntent().getSerializableExtra(TodoEditorViewModel.TODO_DATA);

        mViewModel = ViewModelProviders.of(this).get(TodoEditorViewModel.class);
        mViewModel.initialize(todoData);

        EditText todoName = findViewById(R.id.todoName);
        EditText todoDescription = findViewById(R.id.todoDescription);
        CheckBox doneBox = findViewById(R.id.doneBox);
        Button deleteButton = findViewById(R.id.deleteButton);

        doneBox.setChecked(todoData.isDone());
        todoName.setText(todoData.getName());
        todoDescription.setText(todoData.getDescription());

        todoName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                mViewModel.getTodoData().getValue().setName(todoName.getText().toString());
            }
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
        todoDescription.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                mViewModel.getTodoData().getValue().setDescription(todoDescription.getText().toString());
            }
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        doneBox.setOnCheckedChangeListener((CompoundButton buttonView,boolean isChecked) ->
            mViewModel.getTodoData().getValue().setDone(isChecked)
        );

        deleteButton.setOnClickListener((View view) -> {
            Intent intent = new Intent();
            intent.putExtra("Delete", true);
            intent.putExtra(TodoEditorViewModel.TODO_DATA, mViewModel.getTodoData().getValue());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    public void sendResult() {
        Intent intent = new Intent();
        intent.putExtra("Delete", false);
        intent.putExtra(TodoEditorViewModel.TODO_DATA, mViewModel.getTodoData().getValue());
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    public void onBackPressed() {
        sendResult();
        super.onBackPressed();
    }
}
