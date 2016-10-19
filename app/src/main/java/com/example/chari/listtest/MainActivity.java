package com.example.chari.listtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    DbHelper dbHelper;
    Button image;
    ImageView imageView;
    TextView tex1;
    ListView  listMovie;
    EditText editText,EditMovie2,EditMovie3,EditMovie4,EditMovie5,EditMovie6;
    Button InsertData;
    Bitmap photocap;
    Bitmap thumbnail;
    private int take=1;
    int REQUEST_CAMERA = 0;
    private ProgressDialog pDialog;
    private  WebRequest webre;
    public static String  URL="http://www.practica.eichgi.com/books.php";

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
        tex1=(TextView)findViewById(R.id.tex1);
        image=(Button)findViewById(R.id.imageClick);
        imageView=(ImageView)findViewById(R.id.imageView);
        webre= new WebRequest();


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

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);

        /*Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);*/
    }



    public void Insert(View view)
    {

        new HttpAsyncTaskFromDetails().execute(URL);



        String movie=editText.getText().toString();



        long Inserted= dbHelper.getData(movie, EditMovie2.getText().toString(),EditMovie3.getText().toString(), EditMovie4.getText().toString(),EditMovie5.getText().toString(), thumbnail);

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

        if (requestCode == REQUEST_CAMERA)
        {

            onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data)
    {

        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        imageView.setImageBitmap(thumbnail);

        //this is


    }


    private class HttpAsyncTaskFromDetails extends AsyncTask<String, Void, String>
    {
        private String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls)
        {

            String res1=webre.makeWebServiceCall(urls[0],1);
            Log.e("Result",res1);
             /*  try {
            JSONObject jsonObject=new JSONObject(response.toString());
             user_json = jsonObject.getString("user");
            Log.e(TAG,"user name"+user);
             password_json=jsonObject.getString("Password");
            Log.e(TAG,"user password"+password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(user.getText().toString()==user_json&&password.getText().toString()==password_json)
        {
           // Toast.makeText(,"user password sucess",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"Authantication sucess");
        }else
        {
            Log.e(TAG,"Authantication failed");
        }*/

            return res1;


        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(String.valueOf(result));
            tex1.setText(result);

            pDialog.dismiss();


        }
    }



}
