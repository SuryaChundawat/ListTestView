package com.example.chari.listtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Chari on 9/20/2016.
 */
public class ViewListView extends AppCompatActivity
{
    ListView listView;
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    public static  ArrayList<String> Id = new ArrayList<String>();
    public static  ArrayList<String> Movie = new ArrayList<String>();
    public static  ArrayList<String> Movie2= new ArrayList<String>();
    public static ArrayList<String> Movie3 = new ArrayList<String>();
    public static ArrayList<String> Movie4= new ArrayList<String>();
    public static ArrayList<String> Movie5= new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        dbHelper= new DbHelper(this);



        listView=(ListView)findViewById(R.id.listview);

        //dbHelper.getDataFromData();
        //getDataFromData();






        /*listAdapter = new ArrayAdapter<String>(this, R.layout.row, data);
        listView.setAdapter(listAdapter);*/


    }





    public void getDataFromData()
    {

        String buildSQL = "SELECT * FROM " +dbHelper.TABLE_NAME;

        sqLiteDatabase =dbHelper.getWritableDatabase();
            cursor = sqLiteDatabase.rawQuery(buildSQL, null);


            if (cursor.moveToFirst())
            {
                do {
                    Id.add(cursor.getString(cursor.getColumnIndex(dbHelper._ID)));
                    Movie.add(cursor.getString(cursor.getColumnIndex(dbHelper.LISTOFMOVIE)));
                    Movie2.add(cursor.getString(cursor.getColumnIndex(dbHelper.LISTOFMOVIE1)));
                    Movie3.add(cursor.getString(cursor.getColumnIndex(dbHelper.LISTOFMOVIE2)));
                    Movie4.add(cursor.getString(cursor.getColumnIndex(dbHelper.LISTOFMOVIE3)));
                    Movie5.add(cursor.getString(cursor.getColumnIndex(dbHelper.LISTOFMOVIE4)));
                   } while (cursor.moveToNext());

            }

        CoustomAdapter customAdapter= new CoustomAdapter(getApplicationContext(),Id,Movie,Movie2,Movie3,Movie4,Movie5);
        listView.setAdapter(customAdapter);



        cursor.close();

        Log.e("getAllData SQL: ",""+ Id);
        Log.e("getAllData SQL: ",""+ Movie);
        Log.e("getAllData SQL2: ",""+ Movie2);
        Log.e("getAllData SQL3: ",""+ Movie3);
        Log.e("getAllData SQL4: ",""+ Movie4);
        Log.e("getAllData SQL5: ",""+ Movie5);






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id)
            {

                AllClear();
                startActivity(new Intent(ViewListView.this,Activity1.class));
                finish();


            }
        });





    }

    public void AllClear()
    {

        Id.clear();
        Movie.clear();
        Movie2.clear();
        Movie3.clear();
        Movie4.clear();
        Movie5.clear();
    }



    @Override
    protected void onResume() {
        super.onResume();

        getDataFromData();

    }


}
