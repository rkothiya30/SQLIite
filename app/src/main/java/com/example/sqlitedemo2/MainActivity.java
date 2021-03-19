package com.example.sqlitedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    EditText e1, e2, e3, e4;
    ListView lv1, lv2;
    List<User> a1 , a2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1= (Button) findViewById(R.id.b1);
        b2= (Button) findViewById(R.id.b2);
        b3= (Button) findViewById(R.id.b3);
        b4= (Button) findViewById(R.id.b4);
        e1= (EditText) findViewById(R.id.e1);
        e2= (EditText) findViewById(R.id.e2);
        e3= (EditText) findViewById(R.id.e3);
        e4= (EditText) findViewById(R.id.e4);
        lv1= (ListView) findViewById(R.id.lv1);
        lv2= (ListView) findViewById(R.id.lv2);
        a1 = new ArrayList<User>();
        a2 = new ArrayList<User>();
        DBHelper dbhelper = new DBHelper(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString() );
                a1.add(u);
                ArrayAdapter adapter1 = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1, a1);
                lv1.setAdapter(adapter1);


                boolean b = dbhelper.addUser(u);
                if(b){
                    Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "User not added", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper1 = new DBHelper(MainActivity.this);
                List<User> users = dbhelper1.showData();

                ArrayAdapter adapter2 = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_selectable_list_item, users);
                lv2.setAdapter(adapter2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = e1.getText().toString();
                DBHelper dbhelper2 = new DBHelper(MainActivity.this);
                List<User> u2 = dbhelper2.showData();
                int i=0;
                for (User u : u2){
                    if(u.getName().equals(search)){
                        i = dbhelper2.UpdateUser(u);
                        break;
                    }

                }
                if(i==1){
                    Toast.makeText(MainActivity.this, "User updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "UserName does not match in Database", Toast.LENGTH_SHORT).show();
                }

                List<User> users = dbhelper2.showData();

                ArrayAdapter adapter3 = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_selectable_list_item, users);
                lv2.setAdapter(adapter3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = e1.getText().toString();
                DBHelper dbhelper3 = new DBHelper(MainActivity.this);
                List<User> u3 = dbhelper3.showData();
                int i=0;
                for (User u : u3){
                    if(u.getName().equals(search)){
                        i = dbhelper3.DeleteUser(u);
                        break;
                }}
                    if(i!=0){
                        Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();

                }
                    else{
                    Toast.makeText(MainActivity.this, "UserName does not match in Database", Toast.LENGTH_SHORT).show();
                }
                List<User> users = dbhelper3.showData();

                ArrayAdapter adapter4 = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_selectable_list_item, users);
                lv2.setAdapter(adapter4);
            }
        });




    }
}