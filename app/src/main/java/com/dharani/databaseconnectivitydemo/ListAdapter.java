package com.dharani.databaseconnectivitydemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {
    ArrayList<String> al;
    Context context;
    String id;
    String name;
    String address;


    public ListAdapter(Context context, ArrayList<String> al) {
        super(context,R.layout.listview,al);
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView tvId, tvName, tvAddress;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listview, null, true);

        tvId = (TextView) view.findViewById(R.id.textView_ID);
//        tvName = (TextView) view.findViewById(R.id.textView_Name);
//        tvAddress = (TextView) view.findViewById(R.id.textView_Address);

        Log.e("Name ","name "+name);
        tvId.setText(al.get(position));
//        tvName.setText(al.get(position).toString());
//        tvAddress.setText(al.get(position).toString());

        return view;
    }
}
