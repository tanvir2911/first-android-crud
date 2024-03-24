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

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        name = findViewById(R.id.name);
        email = findViewById(R.id.department);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.submit);
        tv = findViewById(R.id.textView1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String names1 = name.getText().toString();
                String email1 = email.getText().toString();
                String pass = password.getText().toString();

                System.out.println("Email: "+email1);
                System.out.println("Password: "+ pass);

                Database db = new Database(getApplicationContext(), "test",null,1);

                if(email1.length()==0 || pass.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill out all the fields", Toast.LENGTH_LONG).show();
                }else{
                    if(db.login(email1,pass)==1){
                        Toast.makeText(getApplicationContext(),"Logged In ", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Email: "+email1+
                                ", Password: "+pass, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, StudentRegistration.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Wrong data", Toast.LENGTH_LONG).show();
                    }
                }



            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUpActivity.class));
            }
        });
    }
}