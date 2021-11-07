package com.pravinglkp.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText newTask;
    Button addBtn;
    ListView taskList;
    ArrayList<String> taskArray= new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTask = findViewById(R.id.newTask);
        addBtn = findViewById(R.id.addBtn);
        taskList = findViewById(R.id.taskList);

        taskArray= AppData.retriveData(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,taskArray);
        taskList.setAdapter(adapter);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = newTask.getText().toString();
                if(task.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter task to add",Toast.LENGTH_SHORT).show();
                }
                else{
                    taskArray.add(task);
                    newTask.setText("");
                    AppData.storeData(taskArray,getApplicationContext());
                    adapter.notifyDataSetChanged();

                }

            }
        });
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete")
                        .setMessage("Do you want to delete?")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                taskArray.remove(position);
                                AppData.storeData(taskArray,getApplicationContext());
                                adapter.notifyDataSetChanged();
                            }
                        });
                AlertDialog alertDialog=alert.create();
                alertDialog.show();




            }
        });
    }

}