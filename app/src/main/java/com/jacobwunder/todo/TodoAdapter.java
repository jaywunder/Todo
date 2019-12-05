package com.jacobwunder.todo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jacobwunder.todo.data.AppDatabase;
import com.jacobwunder.todo.data.TodoData;
import com.jacobwunder.todo.ui.todoeditor.TodoEditorViewModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<TodoData> mDataset;
    private AppDatabase db;


    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        private AppDatabase db;
        private TodoData data;

        public TextView textView;
        public CheckBox doneBox;
        public TodoViewHolder(ConstraintLayout v, AppDatabase db) {
            super(v);
            this.db = db;
            textView = v.findViewById(R.id.todoName);
            doneBox = v.findViewById(R.id.doneBox);

            textView.setOnClickListener((View view) -> {
                Intent intent = new Intent(view.getContext(), TodoEditor.class);
                System.out.println(data);
                intent.putExtra(TodoEditorViewModel.TODO_DATA, TodoData.copy(data));
                ((Activity) view.getContext()).startActivityForResult(intent, 2);
            });

            doneBox.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                data.setDone(isChecked);
                db.todoDao().update(data);
            });
        }

        public void setData(TodoData data) {
            this.data = data;

            textView.setText(data.getName());
            doneBox.setChecked(data.isDone());
        }
    }

    TodoAdapter(AppDatabase db) {
        this.db = db;
        updateTodos();
    }

    public TodoAdapter.TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_item, parent, false);

        TodoViewHolder vh = new TodoViewHolder(v, db);
        return vh;
    }

    public void onBindViewHolder(TodoViewHolder holder, int position) {
        TodoData data = mDataset.get(position);
        holder.setData(data);
    }

    public void updateTodos() {
        mDataset = db.todoDao().getAll();
    }

    public int getItemCount() {
        return mDataset.size();
    }
}