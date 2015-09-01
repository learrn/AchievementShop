package com.example.xjc.achievementshop;

import android.content.Context;
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
public class AchievementAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    public AchievementAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getDate().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.achievement,null);
            holder = new ViewHolder();
            holder.thing = (TextView)convertView.findViewById(R.id.text_achievement);
            holder.bt = (Button)convertView.findViewById(R.id.but_change2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.thing.setText(getDate().get(position).get("ItemTitle").toString());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AchievementAdapter","你点击了按钮" + position);
            }
        });
        return convertView;

    }

    private final class ViewHolder {
        public TextView thing;
        public Button bt;
    }

    private ArrayList<HashMap<String, Object>> getDate(){
    return null;
    }


}

