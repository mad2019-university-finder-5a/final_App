package com.example.ozedu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    //1.contractor 2.viewholder inner class 3.override 2 methods-getview() and getcount()

    ArrayList<AndroidVersion> items;

    public MyAdapter(Context context, int layout, ArrayList<AndroidVersion> items) {
        super(context, layout);
        this.items = items;
    }

    public void update(ArrayList<AndroidVersion> results)
    {
        items=new ArrayList<>();
        items.addAll(results);
        notifyDataSetChanged();//??this indicate android our listview need gets refresh

    }

    public  class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
        ViewHolder viewHolder;
        if(row==null)
        {
            row= LayoutInflater.from(getContext()).inflate(R.layout.my_list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=row.findViewById(R.id.Androidimage);
            viewHolder.textView=row.findViewById(R.id.Versioname);
            row.setTag(viewHolder);
        }

        else{
            viewHolder= (ViewHolder) row.getTag();
        }

        viewHolder.imageView.setImageResource(items.get(position).image);
        viewHolder.textView.setText(items.get(position).VersionName);

        return row;

    }
}