package com.example.chari.listtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    DbHelper dbHelper;
    Button image;
    ImageView imageView;
    ListView  listMovie;
    EditText editText,EditMovie2,EditMovie3,EditMovie4,EditMovie5,EditMovie6;
    Button InsertData;
    Bitmap photocap;
    private int take=1;

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
        image=(Button)findViewById(R.id.imageClick);
        imageView=(ImageView)findViewById(R.id.imageView);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                open(take);

            }
        });


    }

    public void open(int requestCode)
    {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);
    }



    public void Insert(View view)
    {

        String movie=editText.getText().toString();



        long Inserted= dbHelper.getData(movie, EditMovie2.getText().toString(),EditMovie3.getText().toString(), EditMovie4.getText().toString(),EditMovie5.getText().toString(), photocap);

        if (Inserted>0)
        {
            Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ViewListView.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == take)
        {
            if (requestCode == RESULT_OK && data != null)
            {
                photocap=(Bitmap)data.getExtras().get("data");
                Toast.makeText(getApplicationContext(),"Data Inserted"+photocap,Toast.LENGTH_SHORT).show();
                Log.e("Bitmap is coming",""+photocap);
                imageView.setImageBitmap(photocap);
                Toast.makeText(MainActivity.this, "Successfully capture image .", Toast.LENGTH_LONG).show();
            }
        }
    }
}
