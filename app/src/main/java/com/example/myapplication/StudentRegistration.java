package com.example.myapplication;

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

public class StudentRegistration extends AppCompatActivity {

    EditText name;
    EditText department;
    EditText address;
    EditText phone;
    RadioGroup gender;
    RadioButton male;
    RadioButton female;
    Switch active;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        department = findViewById(R.id.department);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
//        male = findViewById(R.id.male);
//        female = findViewById(R.id.female);
        active = findViewById(R.id.active);
        submit = findViewById(R.id.submit);

        gender.clearCheck();

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
                String department1 = department.getText().toString();
                String address1 = address.getText().toString();
                String phone1 = phone.getText().toString();
                String active1 = active.getText().toString();

                Database db = new Database(getApplicationContext(), "test",null,1);

                if(name1.length()==0 || department1.length()==0 || address1.length()==0 || phone1.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill out all the field.", Toast.LENGTH_SHORT).show();
                }else{

                    int selectedId = gender.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) gender.findViewById(selectedId);
                    String gender1 = radioButton.getText().toString();

                    db.addNewStudent(name1, department1, address1, phone1,  gender1,  active1);
                    Toast.makeText(getApplicationContext(),"Student added to database.", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    department.setText("");
                    address.setText("");
                    phone.setText("");
                    active.setText("");
                }



            }
        });
    }
}