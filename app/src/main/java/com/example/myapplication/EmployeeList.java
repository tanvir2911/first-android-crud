package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

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

        System.out.println(list.size()+"--------------------------------------");
        sa = new SimpleAdapter(this,
                list,
                R.layout.employee_list,
                new String[]{"id", "name", "address", "age", "gender"},
                new int[]{R.id.id, R.id.name, R.id.address, R.id.age, R.id.gender})
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ImageView editBtn = v.findViewById(R.id.edit_btn);
                ImageView deleteBtn = v.findViewById(R.id.delete_btn);

                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Edit Button clicked");
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Delete Button clicked"+position);
                        HashMap<String, String> employee = (HashMap<String, String>) list.get(position);

                        db.deleteEmployee(Integer.parseInt(employee.get("id")));

                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });


                return v;
            }
        };

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(sa);
    }
}