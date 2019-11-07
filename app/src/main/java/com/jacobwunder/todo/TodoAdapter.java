package com.jacobwunder.todo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jacobwunder.todo.data.TodoData;
import com.jacobwunder.todo.ui.todoeditor.TodoEditorViewModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<TodoData> mDataset;


    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        private TodoData data;

        public TextView textView;
        public CheckBox doneBox;
        public TodoViewHolder(ConstraintLayout v) {
            super(v);
            textView = v.findViewById(R.id.todoName);
            doneBox = v.findViewById(R.id.doneBox);

            textView.setOnClickListener((View view) -> {
                Intent intent = new Intent(view.getContext(), TodoEditor.class);
                intent.putExtra(TodoEditorViewModel.TODO_DATA, data);
                view.getContext().startActivity(intent);
            });
        }

        public void setData(TodoData data) {
            this.data = data;

            textView.setText(data.getName());
            doneBox.setChecked(data.isDone());
        }
    }

    public TodoAdapter(List<TodoData> todoDataset) {
        mDataset = todoDataset;
    }

    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_item, parent, false);

        TodoViewHolder vh = new TodoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        TodoData data = mDataset.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}