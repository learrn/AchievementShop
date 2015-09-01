package com.example.xjc.achievementshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XJC on 2015/8/31.
 */
public class DesireActivity extends Activity{
    private List<Desire> desireList = new ArrayList<Desire>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desire);
        initDesire();
        DesireAdapter adapter=new DesireAdapter(DesireActivity.this,R.layout.desire_item,desireList);
        ListView listview =(ListView)findViewById(R.id.List_desire);
        listview.setAdapter(adapter);
    }

    private void initDesire() {
        Desire thing1 = new Desire("玩1小时游戏",R.id.desire_button);
        desireList.add(thing1);
        Desire thing2 = new Desire("玩2小时游戏",R.id.desire_button);
        desireList.add(thing2);
        Desire thing3 = new Desire("玩3小时游戏",R.id.desire_button);
        desireList.add(thing3);
        Desire thing4 = new Desire("玩4小时游戏",R.id.desire_button);
        desireList.add(thing4);
    }
}
