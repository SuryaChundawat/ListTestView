package com.example.chari.listtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chari on 9/20/2016.
 */
public class CoustomAdapter extends BaseAdapter
{

    Context context;
    ArrayList<String> Id;
    ArrayList<String> Movie;
    ArrayList<String> Movie2;
    ArrayList<String> Movie3;
    ArrayList<String> Movie4;
    ArrayList<String> Movie5;
    LayoutInflater inflater;

    public CoustomAdapter(Context applicationContext, ArrayList<String> Id,ArrayList<String> Movie, ArrayList<String> Movie2, ArrayList<String> Movie3, ArrayList<String> Movie4, ArrayList<String> Movie5)
    {
        this.context=context;
        this.Id=Id;
        this.Movie=Movie;
        this.Movie2=Movie2;
        this.Movie3=Movie3;
        this.Movie4=Movie4;
        this.Movie5=Movie5;
        inflater=(LayoutInflater.from(applicationContext));


    }

    @Override
    public int getCount()
    {
        return Movie.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent )
    {
        Holder holder;

        LayoutInflater layoutInflater;

        if(child==null)
        {

            //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

           // child = layoutInflater.inflate(R.layout.row, null);
            child=inflater.inflate(R.layout.row,null);

            holder = new Holder();
            holder.Id = (TextView) child.findViewById(R.id.IdMovie);
            holder.Movie = (TextView) child.findViewById(R.id.TextView);
            holder.Movie = (TextView) child.findViewById(R.id.TextView);
            holder.Movie2 = (TextView) child.findViewById(R.id.TextView1);
            holder.Movie3 = (TextView) child.findViewById(R.id.TextView2);
            holder.Movie4 = (TextView) child.findViewById(R.id.TextView3);
            holder.Movie5 = (TextView) child.findViewById(R.id.TextView4);



        }else {

            holder = (Holder) child.getTag();
        }

        holder.Movie.setText(Movie.get(position));
        holder.Movie2.setText(Movie2.get(position));
        holder.Movie3.setText(Movie3.get(position));
        holder.Movie4.setText(Movie4.get(position));
        holder.Movie5.setText(Movie5.get(position));



        return child;


       /* child=inflater.inflate(R.layout.row,null);
        TextView item1=(TextView)child.findViewById(R.id.TextView);
        return null;*/
    }


    public class Holder {
        TextView Id;
        TextView Movie;
        TextView Movie2;
        TextView Movie3;
        TextView Movie4;
        TextView Movie5;
    }

}
