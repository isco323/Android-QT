package com.example.lab2;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataDynamic extends Activity {
    ArrayList<String> mystringarray =  new ArrayList<String>();
    ArrayList<String> selecteddata = new ArrayList<String>();
    ArrayAdapter<String> TextAdapter;
    DatabaseHandler db = new DatabaseHandler(this);
    User user = new User();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_dynamic);

        ListView listview = findViewById(R.id.list_view);
        Button buttonadd = findViewById(R.id.buttonadd);
        Button buttondelete = findViewById(R.id.buttondelete);
        Button buttonedit = findViewById(R.id.buttonedit);
        Button buttonback = findViewById(R.id.buttonback);

        List<User> userslist = db.getAllUsers();
        for(int i = 0; i < userslist.size(); i++)
        {
            mystringarray.add(userslist.get(i)._login + "\t" + userslist.get(i)._pass);
        }

        Bundle extras = getIntent().getExtras();
        int dataid = Integer.parseInt(extras.get("data").toString());
        for(int i = 0; i<userslist.size(); i++) {
            if(userslist.get(i).getID()==dataid)
            {
                user = userslist.get(i);
            }
            Log.d("MyLogs", "datareceived");
        }

        TextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mystringarray);
        listview.setAdapter(TextAdapter);

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EditText data = findViewById(R.id.data);
                        EditText pass = findViewById(R.id.password);
                        userslist.add(new User(data.getText().toString(), pass.getText().toString()));
                        db.addUser(new User(data.getText().toString(), pass.getText().toString()));
                        mystringarray.add(data.getText().toString()+"\t"+ pass.getText().toString());
                        listview.post(new Runnable() {
                            @Override
                            public void run() {
                                data.setText("");
                                pass.setText("");
                                TextAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                }).start();


            }
        });



        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EditText data = findViewById(R.id.data);
                        for(int i = 0; i < userslist.size();i++){
                            if(data.getText().toString().equals(userslist.get(i)._login))
                            {
                                db.deleteUser(userslist.get(i));
                                mystringarray.remove(i);
                                userslist.remove(i);
                            }

                        }
                        listview.post(new Runnable() {
                            @Override
                            public void run() {
                                data.setText("");

                                TextAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();

            }
        });
        buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EditText data = findViewById(R.id.data);
                        EditText pass = findViewById(R.id.password);
                        for(int i = 0; i < userslist.size(); i++) {
                            if (data.getText().toString().equals(userslist.get(i)._login)) {
                                db.chngpsw(user, pass.getText().toString());
                                user.setPass(pass.getText().toString());
                                mystringarray.remove(i);
                                mystringarray.add(i, user.getLogin() + "\t" + user.getPass());
                                listview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        data.setText("");
                                        pass.setText("");
                                        TextAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    }
                }).start();

            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // serialize();
                Intent backint =  new Intent(DataDynamic.this, formregistration.class);
                startActivity(backint);
                finish();
            }



        });
    }

    //    private void serialize() {
//        try {
//            String str = "";
//            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//            for(int i = 0; i<mystringarray.size();i++)
//            {
//                str = mystringarray.get(i);
//                fos.write(str.getBytes(StandardCharsets.UTF_8));
//            }
//            fos.close();
//            Log.i("Logs","Success");
//        }
//        catch (FileNotFoundException e)
//        {
//            Log.i("Logs",e.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//    private boolean deserialize(){
//        try {
//            String str = "";
//            FileInputStream fis = openFileInput(FILENAME);
//            fis.read(str.getBytes());
//            mystringarray.add(str.toString());
//            String data = fis.toString();
//            fis.close();
//            Log.i("Logs","Success");
//            return true;
//        }
//        catch (FileNotFoundException e)
//        {
//            Log.i("Logs",e.toString());
//            return false;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }x
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
