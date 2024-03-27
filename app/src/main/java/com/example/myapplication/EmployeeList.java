package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EmployeeList extends AppCompatActivity {

    ArrayList list;

    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Database db = new Database(getApplicationContext(), "test", null,1);

        list = new ArrayList<>();
        list = db.getEmployees();

        sa = new SimpleAdapter(this,
                list,
                R.layout.employee_list,
                new String[]{"id", "name", "address", "age", "gender"},
                new int[]{R.id.id, R.id.name, R.id.address, R.id.age, R.id.gender}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
               View v= super.getView(position, convertView, parent);
                return v;
            }
        };

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(sa);
    }
}