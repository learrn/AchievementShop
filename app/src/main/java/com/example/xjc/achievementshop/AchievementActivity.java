package com.example.xjc.achievementshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by XJC on 2015/8/31.
 */
public class AchievementActivity extends Activity {

    private Button change;
    private AchievementDB achievementDB;
    private SQLiteDatabase dbReader;
    private ListView listview;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);
        achievementDB = new AchievementDB(this);
        dbReader = achievementDB.getReadableDatabase();
        listview =(ListView)findViewById(R.id.List_achievement);
        change = (Button)findViewById(R.id.but_change2);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(AchievementActivity.this,Achievement_add.class);
                startActivity(intent1);
            }
        });
    }
    public void achSelectDB(){
        this.cursor=this.dbReader.query(AchievementDB.TABLE_NAME1,null,null,null,null,null,null);
        AchievementAdapter achievementAdapter = new AchievementAdapter(this,this.cursor);
        this.listview.setAdapter(achievementAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        achSelectDB();
    }
    
    @Override
    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
    }



}
