package com.example.chari.listtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    DbHelper dbHelper;
    ListView  listMovie;
    EditText editText,EditMovie2,EditMovie3,EditMovie4,EditMovie5,EditMovie6;
    Button InsertData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DbHelper(this);
        editText=(EditText)findViewById(R.id.EditMoviewName);
        EditMovie2=(EditText)findViewById(R.id.EditMovie1);
        EditMovie3=(EditText)findViewById(R.id.EditMoview2);
        EditMovie4=(EditText)findViewById(R.id.EditMoview3);
        EditMovie5=(EditText)findViewById(R.id.EditMoview4);
        InsertData=(Button)findViewById(R.id.InsertData);


    }

    public void Insert(View view)
    {

        String movie=editText.getText().toString();


        boolean Inserted= dbHelper.getData(movie, EditMovie2.getText().toString(),EditMovie3.getText().toString(), EditMovie4.getText().toString(),EditMovie5.getText().toString());

        if (Inserted==true)
        {
            Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ViewListView.class);
            startActivity(i);
        }
    }

}
