package com.jacobwunder.todo.ui.todoeditor;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jacobwunder.todo.R;
import com.jacobwunder.todo.data.TodoData;

public class TodoEditorFragment extends Fragment {
    private TodoData mTodoData;

    private TodoEditorViewModel mViewModel;

    public static TodoEditorFragment newInstance() {
        return new TodoEditorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.todo_editor_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TodoEditorViewModel.class);

        mViewModel.getTodoData().observe(this, this::updateView);

        mViewModel.initializeFromBundle(getArguments());
    }

    public void updateView(TodoData todoData) {
        System.out.print("HELLOOOOOOOOOOOO ");
        System.out.println(todoData.getName());

        EditText todoName = getView().findViewById(R.id.todoName);
        EditText todoDescription = getView().findViewById(R.id.todoDescription);

        todoName.setText(todoData.getName());
        todoDescription.setText(todoData.getDescription());
    }
}
