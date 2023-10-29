package com.example.lab2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;


public class formregistration extends Activity {
    Button buttonenter, buttonleave, checkbutton;
    EditText Email, password;
    Intent openIntent;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterleave);
        buttonenter = findViewById(R.id.buttonenter);
        buttonleave = findViewById(R.id.buttonleave);
        Email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        openIntent = new Intent(formregistration.this, DataDynamic.class);
        checkbutton = findViewById(R.id.checkbutton);
        sharedPreferences = this.getPreferences((Context.MODE_PRIVATE));
        editor = sharedPreferences.edit();
        checkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setText(sharedPreferences.getString("mail", ""));
                password.setText(sharedPreferences.getString("pass", ""));
            }
        });

        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check())
                {
                    String data;
                    data = "Email: " + Email.getText().toString() + "\nPassword: "+ password.getText().toString();
                    editor.putString("mail","" + Email.getText());
                    editor.putString("pass",""+ password.getText());
                    editor.apply();
                    openIntent.putExtra("data",data);
                    startActivity(openIntent);
                    finish();
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
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Logs", "app started");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Logs", "app paused");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Logs", "app resumed");


    }
@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Logs", "app destroy");

    }
}
