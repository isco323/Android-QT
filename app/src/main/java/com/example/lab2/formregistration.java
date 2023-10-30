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
    Button buttonenter, buttonleave, buttonload;
    EditText Email, password;
    DatabaseHandler db = new DatabaseHandler(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterleave);
        buttonenter = findViewById(R.id.buttonenter);
        buttonleave = findViewById(R.id.buttonleave);
        buttonload = findViewById(R.id.buttonload);
        Email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        Intent openIntent = new Intent(formregistration.this, DataDynamic.class);
        buttonload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = db.getAllUsers();
                for (User usr : users) {
                    String log = "Id: "+usr.getID()+" ,Login: " + usr.getLogin() + " ,Password: " + usr.getPass();
                    Log.v("Loading...", log);
                }
            }
        });
        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check())
                {
                    String data;
                    data = "Email: " + Email.getText().toString() + "\nPassword: "+ password.getText().toString();
                    db.addUser (new User(Email.getText().toString(), password.getText().toString()));
                    openIntent.putExtra("data",data);
                    startActivity(openIntent);
                }
            }
            private boolean check() {
                Toast mytoast = new Toast(formregistration.this);
                if(Email.getText().toString().equals(""))
                {
                    mytoast.makeText(formregistration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(password.getText().toString().equals(""))
                {
                    mytoast.makeText(formregistration.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }

        });
        buttonleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setText("");
                password.setText("");
                db.deleteUser(new User(Email.getText().toString(), password.getText().toString()));
                List<User> users = db.getAllUsers();
                for (User usr : users) {
                    String log = "Id: "+usr.getID()+" ,Login: " + usr.getLogin() + " ,Password: " + usr.getPass();
                    Log.v("Loading...", log);
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
        db.close();
    }

}
