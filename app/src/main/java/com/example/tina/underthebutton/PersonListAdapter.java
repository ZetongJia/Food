package com.example.tina.underthebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apple on 18/1/28.
 */

public class PersonListAdapter extends ArrayAdapter<FoodTruck> {
    private Context mContext;
    private int mResource;


    public PersonListAdapter(Context context, int resource, ArrayList<FoodTruck> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();

        String rating = getItem(position).getRating();

        int id = getItem(position).getID();

        //Create the person object with the information
        FoodTruck ft = new FoodTruck(name, rating, id, getItem(position).getLat(), getItem(position).getLon());

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvRating = (TextView) convertView.findViewById(R.id.textView2);

        tvName.setText(name);
        tvRating.setText(rating);

        return convertView;
    }
}
