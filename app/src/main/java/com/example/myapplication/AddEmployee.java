package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEmployee extends AppCompatActivity {


    EditText name;
    EditText address;
    EditText age;
    RadioGroup gender;
    Button submit;
    TextView backToHome;



    String updateId =null;
    String updateName = null;
    String updateAddress = null;
    String updateAge = null;
    String updateGender = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);
        backToHome = findViewById(R.id.backToHome);

        gender.clearCheck();



        Intent intent =getIntent();
        updateId = intent.getStringExtra("id");
        updateName = intent.getStringExtra("name");
        updateAddress = intent.getStringExtra("address");
        updateAge = intent.getStringExtra("age");
        updateGender = intent.getStringExtra("gender");

        name.setText(updateName);
        address.setText(updateAddress);
        age.setText(updateAge);


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String address1 = address.getText().toString();
                String age1 = age.getText().toString();

                Database db = new Database(getApplicationContext(), "test",null,1);

                if(name1.length()==0 ||  address1.length()==0 || age1.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill out all the field.", Toast.LENGTH_SHORT).show();
                }else{

                    int selectedId = gender.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) gender.findViewById(selectedId);
                    String gender1 = radioButton.getText().toString();

                    if(updateId != null){
                        db.updateEmployee(Integer.valueOf(updateId),name1, address1, age1,  gender1);
                        startActivity(new Intent(AddEmployee.this, EmployeeList.class));
                    }else{
                        db.addNewEmployee(name1, address1, age1,  gender1  );
                        Toast.makeText(getApplicationContext(),"Employee added to database.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddEmployee.this, EmployeeList.class));
                    }



                    name.setText("");
                    address.setText("");
                    age.setText("");
                }



            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEmployee.this, Home.class));
            }
        });

    }
}