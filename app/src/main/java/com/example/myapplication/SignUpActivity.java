package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText phone;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        name = findViewById(R.id.name);
        email = findViewById(R.id.department);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        btn = findViewById(R.id.submit);
        tv = findViewById(R.id.textView1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                String phone1 = phone.getText().toString();

                Toast.makeText(getApplicationContext(),"Name: "+name1+"\n, Email: "+email1+"\n, Password: "+pass + "\n, Phone: "+phone1, Toast.LENGTH_LONG).show();


                Database db = new Database(getApplicationContext(), "test",null,1);

                if(name1.length()==0 || email1.length()==0 || pass.length()==0 || phone1.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill out all the field.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addNewUser(name1,email1,pass,phone1);
                    startActivity(new Intent(SignUpActivity.this, Login.class));
                }




            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, Login.class));
            }
        });
    }
}