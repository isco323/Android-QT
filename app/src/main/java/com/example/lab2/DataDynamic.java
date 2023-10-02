package com.example.lab2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataDynamic extends Activity{
    ArrayList<String> mystringarray = new ArrayList<String>();
    ArrayList<String> selecteddata = new ArrayList<String>();
    ArrayList<String> serialization = new ArrayList<>();
    ArrayAdapter<String> TextAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_dynamic);
        TextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, mystringarray);
        ListView listview = findViewById(R.id.list_view);
        Button buttonadd = findViewById(R.id.buttonadd);
        Button buttondelete = findViewById(R.id.buttondelete);
        Button buttonedit = findViewById(R.id.buttonedit);
        Button buttonback = findViewById(R.id.buttonback);
        listview.setAdapter(TextAdapter);


        Bundle extras = getIntent().getExtras();
        String data = null;
        if(extras!=null)
        {
            data = extras.getString("data");
            Log.d("MyLogs", "datareceived");
        }
        TextAdapter.add(data.toString());
        TextAdapter.notifyDataSetChanged();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String data = TextAdapter.getItem(position);
                if(listview.isItemChecked(position))
                    selecteddata.add(data);
                else
                    selecteddata.remove(data);
            }

        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText data = findViewById(R.id.data);
                String content = data.getText().toString();
                if(!content.isEmpty())
                {
                    TextAdapter.add(content);
                    data.setText("");
                    TextAdapter.notifyDataSetChanged();
                }

            }
    });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i< selecteddata.size();i++){
                    TextAdapter.remove(selecteddata.get(i));
                }
                listview.clearChoices();
                selecteddata.clear();
                TextAdapter.notifyDataSetChanged();
            }
        });
        buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText data = findViewById(R.id.data);
                String content = data.getText().toString();
                if(selecteddata.size() > 1)
                {
                    toasterr("Error, выберите один элемент для редактирования", buttonedit);
                }
                if(selecteddata.size() == 0)
                {
                    toasterr("Error, выберите элементы для редактирования", buttonedit);
                }
                if((selecteddata.size() == 1) && content.equals(""))
                {
                    toasterr("Error, введите текст для редактирования", buttonedit);
                }
                if((selecteddata.size() == 1) && !content.equals(""))
                {
                    TextAdapter.remove(selecteddata.get(0));
                    data.setText("");
                    dateshow();
                    content+="\nlast edit time: ";
                    content+=dateshow();
                    TextAdapter.add(content);
                    listview.clearChoices();
                    selecteddata.clear();
                    TextAdapter.notifyDataSetChanged();
                }

            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialize();
                Intent backint =  new Intent(DataDynamic.this, formregistration.class);
                startActivity(backint);
                finish();
            }

            private void serialize() {
                try{
                    FileOutputStream fos = new FileOutputStream("List");
                    ObjectOutputStream oos;
                }
                catch (FileNotFoundException e)
                {
                    Log.i("Logs","File not found");
                }

            }

        });
    }
    private String dateshow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(date);
    }

    @Override
    public void onBackPressed() {

    }

    private void toasterr(String text, Button btn) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
