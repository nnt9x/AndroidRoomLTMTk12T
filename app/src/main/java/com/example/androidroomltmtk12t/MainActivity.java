package com.example.androidroomltmtk12t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.androidroomltmtk12t.dao.TodoDAO;
import com.example.androidroomltmtk12t.database.AppDatabase;
import com.example.androidroomltmtk12t.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Config DB
    private AppDatabase db;
    private TodoDAO todoDAO;

    // View
    private EditText edtInput;
    private ListView lvTodos;

    // Adapter va listview
    private ArrayAdapter<String> myAdapter;
    private List<String> dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind id
        edtInput = findViewById(R.id.edtInput);
        lvTodos = findViewById(R.id.lvTodos);

        // Init DB
        db = Room.databaseBuilder(this, AppDatabase.class,"myDB")
                .allowMainThreadQueries().build();
        todoDAO = db.getTodoDAO();
        // Tiếp tục ghép giao diện

        // Lay du lieu trong db -> List<Todó> vấn đề -> khác List<String> sử dụng trong ArrayAdapter
        dataSource = new ArrayList<>();
        todoDAO.getAll().stream().forEach(todo ->{
            dataSource.add(todo.getTitle());
        });

        // Tao Adaptẻr
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSource);
        // SetAdapter
        lvTodos.setAdapter(myAdapter);
    }

    public void addNewTodo(View view) {
        // Them todos
        String todoTitle = edtInput.getText().toString().trim();
        if(todoTitle.isEmpty()){
            edtInput.setError("Hay them du lieu");
            return;
        }
        // Co du lieu: them vao database va datasource
        dataSource.add(todoTitle);
        todoDAO.create(new Todo(todoTitle));
        myAdapter.notifyDataSetChanged();

        // Reset text
        edtInput.setText("");

    }
}