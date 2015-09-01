package com.example.xjc.achievementshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XJC on 2015/8/31.
 */
public class AchievementActivity extends Activity {
    private List<Desire> achievementList = new ArrayList<Desire>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);
        initDesire();
        DesireAdapter adapter=new DesireAdapter(AchievementActivity.this,R.layout.desire_item,achievementList);
        ListView listview =(ListView)findViewById(R.id.List_achievement);
        listview.setAdapter(adapter);
    }

    private void initDesire() {
        Desire thing1 = new Desire("学习1小时",R.id.desire_button);
        achievementList.add(thing1);
        Desire thing2 = new Desire("学习2小时",R.id.desire_button);
        achievementList.add(thing2);
        Desire thing3 = new Desire("学习3小时",R.id.desire_button);
        achievementList.add(thing3);
        Desire thing4 = new Desire("学习4小时",R.id.desire_button);
        achievementList.add(thing4);
    }
}
