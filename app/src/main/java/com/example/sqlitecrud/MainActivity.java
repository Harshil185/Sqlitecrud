package com.example.sqlitecrud;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitecrud.handler.DBHandler;
import com.example.sqlitecrud.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHandler db;
    EditText et_id, et_name, et_desc;
    ListView lst_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DBHandler(this);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_desc = findViewById(R.id.et_desc);
        lst_show = findViewById(R.id.lst_show);

    }

    public void Insert(View view){
        String name = et_name.getText().toString();
        String desc = et_desc.getText().toString();

        Task task = new Task();
        task.setTaskName(name);
        task.setTaskDesc(desc);

        if (db.Insert_Record(task)){
            Toast.makeText(this, "Inserted Successfully!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
        }
        Read();
    }

    public void Read(){
        ArrayList<String> data = db.Read_Records();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lst_show.setAdapter(adapter);
    }

    public void Update(View view){
        String id = et_id.getText().toString();
        String name = et_name.getText().toString();
        String desc = et_desc.getText().toString();

        Task task = new Task();
        task.setId(Integer.parseInt(id));
        task.setTaskName(name);
        task.setTaskDesc(desc);

        if (db.Update_Record(task)){
            Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
        }
        Read();
    }

    public void Delete(View view){
        String id = et_id.getText().toString();

        if (db.Delete_Record(id)){
            Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
        }
        Read();
    }

}
