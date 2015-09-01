package com.example.xjc.achievementshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XJC on 2015/8/31.
 */
public class DesireAdapter extends ArrayAdapter<Desire> {
    private int resourceId;
    public DesireAdapter(Context context,int textViewResourceId, List<Desire> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Desire desire = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView desireThing = (TextView)view.findViewById(R.id.desire_thing);
        Button desireButton = (Button)view.findViewById(R.id.desire_button);
        desireThing.setText(desire.getThing());
        desireButton.setText(desire.getButtonid());
        return view;
    }
}
