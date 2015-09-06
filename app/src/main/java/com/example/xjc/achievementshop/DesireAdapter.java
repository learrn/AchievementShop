package com.example.xjc.achievementshop;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by XJC on 2015/8/31.
 */
public class DesireAdapter extends BaseAdapter {
    private Cursor cursor;
    private LayoutInflater mInflater;

    public DesireAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.desire_item, null);
            holder = new ViewHolder();
            holder.thing = (TextView) convertView.findViewById(R.id.desire_thing);
            holder.point = (TextView) convertView.findViewById(R.id.desire_point);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.thing.setText(getDate().get(position).get("ItemTitle").toString());
        holder.point.setText(getDate().get(position).get("ItemPoint").toString());
        return convertView;

    }


    private final class ViewHolder {
        public TextView thing;
        public TextView point;
        public Button bt;
    }

    private ArrayList<HashMap<String, Object>> getDate() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            String ItemTitle = cursor.getString(cursor.getColumnIndex("Des_thing"));
            String ItemPoint = cursor.getString(cursor.getColumnIndex("Des_point"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", ItemTitle);
            map.put("ItemPoint", ItemPoint+"分");
            listItem.add(map);
        }
        return listItem;
    }


}

