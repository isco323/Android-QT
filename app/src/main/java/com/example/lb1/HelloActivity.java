package com.example.lb1;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;

import kotlinx.coroutines.Delay;

public class HelloActivity extends Activity {
    int sch = 0;
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button_reset = findViewById(R.id.button_reset);
        TextView text1 = findViewById(R.id.text1);

        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                button1.setText("Нажато");
                text1.setText(Integer.toString(sch));
                ++sch;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                button2.setText("Clicked!");
                text1.setText(Integer.toString(sch));
                ++sch;
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sch = 0;
                text1.setText(Integer.toString(sch));
                button1.setText("НАЖМИ!");
                button2.setText("CLICK!");
            }
        });
        button_reset.setOnTouchListener(new View.OnTouchListener() {
            private int count = 0;
            private final long DELAY = 250;
            Handler handler = new Handler();
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        handler.postDelayed(action, DELAY);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacks(action);
                        button1.setBackgroundColor(Color.rgb(214, 215, 215));
                        button2.setBackgroundColor(Color.rgb(214, 215, 215));
                }
                return false;
            }
            private Runnable action = new Runnable() {
                @Override
                public void run() {
                    colorchanger();
                    handler.postDelayed(action, DELAY);
                }
            };
            void colorchanger(){
                Random random = new Random();
                button1.setBackgroundColor(Color.rgb(random.nextInt(), random.nextInt(), random.nextInt()));
                button2.setBackgroundColor(Color.rgb(random.nextInt(), random.nextInt(), random.nextInt()));
            }
        });
    }

}
