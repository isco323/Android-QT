package com.example.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class formregistration extends Activity {
    Button buttonenter, buttonleave;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterleave);
        buttonenter = findViewById(R.id.buttonenter);
        buttonleave = findViewById(R.id.buttonleave);
        TextView Email = findViewById(R.id.editTextTextEmailAddress);

        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnclick(view);
            }
        });
        buttonleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnclick(view);
            }
        });
    }
    public void btnclick(View v){
        showafonclick(((Button) v). getText().toString(), ((Button) v));

    }
    private void showafonclick(String text, Button btn) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }

}
