package com.example.chari.listtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chari on 9/20/2016.
 */
public class DbHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME="test.db";
    public static final String TABLE_NAME="list_view";

    public static final String _ID="_id";
    public static final String LISTOFMOVIE="moview_name";
    public static final String LISTOFMOVIE1="moview_name1";
    public static final String LISTOFMOVIE2="moview_name2";
    public static final String LISTOFMOVIE3="moview_name3";
    public static final String LISTOFMOVIE4="moview_name4";

    private static final String TAG = "DbHelper";

    SQLiteDatabase sqLiteDatabase;




    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public final String CREATE_TABLE_MOVIE= "CREATE TABLE "+TABLE_NAME+"( "+
            _ID+" INTEGER PRIMARY KEY , "+
            LISTOFMOVIE+" TEXT NOT NULL , "+
            LISTOFMOVIE1+" TEXT NOT NULL , "+
            LISTOFMOVIE2+" TEXT NOT NULL , "+
            LISTOFMOVIE3+" TEXT NOT NULL , "+
            LISTOFMOVIE4+" TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_MOVIE);

    }




    public boolean getData(String MovieName, String Movie2,String Movie3 ,String Movie4, String Movie5)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LISTOFMOVIE, MovieName);
        cv.put(LISTOFMOVIE1, Movie2);
        cv.put(LISTOFMOVIE2, Movie3);
        cv.put(LISTOFMOVIE3, Movie4);
        cv.put(LISTOFMOVIE4, Movie5);


        long redult=db.insert(TABLE_NAME,null,cv);

        if (redult==-1)
        {

            return false;
        }else
        {

            return true;
        }



    }


   /* public Integer update2(String id)
    {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        return  sqLiteDatabase.delete(TABLE_NAME,"_id",new String[]{id});




    }*/

    public boolean update(String id,String movie, String movie2, String movie3, String movie4, String movie5)
    {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(_ID,id);
        cv.put(LISTOFMOVIE,movie);
        cv.put(LISTOFMOVIE1,movie2);
        cv.put(LISTOFMOVIE2,movie3);
        cv.put(LISTOFMOVIE3,movie4);
        cv.put(LISTOFMOVIE4,movie5);

        sqLiteDatabase.update(TABLE_NAME,cv,"_id=?", new String[]{id});
        return true;

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
