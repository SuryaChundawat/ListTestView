package com.example.chari.listtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chari on 9/20/2016.
 */
public class Activity1 extends AppCompatActivity
{

    Button update;
    EditText id,editMovie,editMovie1,editMovie2,editMovie3,editMovie4;
    DbHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update1);
        update=(Button)findViewById(R.id.update);
        id=(EditText)findViewById(R.id.id);
        editMovie=(EditText)findViewById(R.id.editMovie);
        editMovie1=(EditText)findViewById(R.id.editMovie1);
        editMovie2=(EditText)findViewById(R.id.editMovie2);
        editMovie3=(EditText)findViewById(R.id.editMovie3);
        editMovie4=(EditText)findViewById(R.id.editMovie4);
        dbHelper= new DbHelper(this);
        Update();


    }

    public void Update()
    {

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {



                boolean Isupdateted=dbHelper.update(id.getText().toString(),editMovie.getText().toString(),editMovie1.getText().toString(),editMovie2.getText().toString(),editMovie3.getText().toString(),editMovie4.getText().toString());

                if (Isupdateted==true)
                {
                    Toast.makeText(getApplicationContext(),"Data is Updated",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Activity1.this,MainActivity.class));
                    finish();

                }else
                {

                    Toast.makeText(getApplicationContext(),"Data is not Updated",Toast.LENGTH_SHORT).show();

                }



            }
        });




    }


}
