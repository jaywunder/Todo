package com.jacobwunder.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobwunder.todo.data.AppDatabase;
import com.jacobwunder.todo.data.TodoData;
import com.jacobwunder.todo.ui.todoeditor.TodoEditorViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    private RecyclerView recyclerView;
    private TodoAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "todos"
        ).allowMainThreadQueries().build();

//        db.todoDao().insert(new TodoData("Make a database"));

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {
            Intent intent = new Intent(view.getContext(), TodoEditor.class);
            intent.putExtra(TodoEditorViewModel.TODO_DATA, new TodoData());
            ((Activity) view.getContext()).startActivityForResult(intent, 1);
        });

        recyclerView = findViewById(R.id.todo_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TodoAdapter(db);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("FROM MAINACTIVITY");
        System.out.println(requestCode);
        System.out.println(resultCode);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (!(boolean) data.getSerializableExtra("Delete")) {
                TodoData todoData = (TodoData) data.getSerializableExtra(TodoEditorViewModel.TODO_DATA);
                db.todoDao().insert(todoData);
            }
            mAdapter.updateTodos();
            mAdapter.notifyDataSetChanged();
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            TodoData todoData = (TodoData) data.getSerializableExtra(TodoEditorViewModel.TODO_DATA);

            if ((boolean) data.getSerializableExtra("Delete")) {
                db.todoDao().delete(todoData);
            } else {
                db.todoDao().update(todoData);
            }
            mAdapter.updateTodos();
            mAdapter.notifyDataSetChanged();
        }
    }
}
