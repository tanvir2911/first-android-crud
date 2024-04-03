package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiCall extends AppCompatActivity {

    EditText userName;
    EditText email;
    EditText password;

    Button submit;
    Button update;
    Button getAll;

    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        update = findViewById(R.id.update);
        getAll = findViewById(R.id.getAll);
        data = findViewById(R.id.data);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName1 = userName.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.20.46:4000/signup";
                try {
                    JSONObject paramValue = new JSONObject();
                    paramValue.put("username", userName1);
                    paramValue.put("email", email1);
                    paramValue.put("password", password1);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, paramValue, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject s) {
                            System.out.println("Response:  " + s);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("Error......" + volleyError.getLocalizedMessage());
                        }
                    }
                    );
                    queue.add(jsonObjectRequest);
                } catch (JSONException e) {

                }

            }
        });

        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("Hello");

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.20.46:4000/signup";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
//                        System.out.println("Response:  "+s);

                        data.setText("");
                        try {
                            JSONArray ja = new JSONArray(s);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jo = ja.getJSONObject(i);
                                String userName = jo.getString("username");
                                String email = jo.getString("email");
                                String pass = jo.getString("password");

                                System.out.println("UserName: " + userName + " Email: " + email);

                                data.append("userName: " + userName + " Email: " + email + "\n");

                            }

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("Error......" + volleyError.getLocalizedMessage());
                    }
                }
                );
                queue.add(stringRequest);
            }
        });

    }
}