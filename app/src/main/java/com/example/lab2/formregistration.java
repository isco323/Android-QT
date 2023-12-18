package com.example.lab2;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class formregistration extends Activity {
    Button buttonenter;
    EditText Email, password;
    DatabaseHandler db = new DatabaseHandler(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterleave);
        buttonenter = findViewById(R.id.buttonenter);
        Email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = db.getAllUsers();
                User user;
                for (int i = 0; i < userList.size(); i++) {
                    user = userList.get(i);
                    if (Email.getText().toString().equals(user.getLogin()) &&
                            password.getText().toString().equals(user.getPass())) {
                        Intent openIntent = new Intent(formregistration.this, DataDynamic.class);
                        openIntent.putExtra("data", user.getID());
                        finish();
                        formregistration.this.startActivity(openIntent);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Logs", "app started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Logs", "app destroy");
    }

}
