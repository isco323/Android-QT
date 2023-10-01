package com.example.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Intent openIntent = new Intent(formregistration.this, DataDynamic.class);
        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(openIntent);
                openIntent.putExtra("data","datafromfirstactivity");
            }
        });
        buttonleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.i("AppLogger", "Сработал старт");
//        Toast mytoast = new Toast(this);
//        mytoast.makeText(formregistration.this, "Сработал старт", Toast.LENGTH_SHORT).show();
    }

}
