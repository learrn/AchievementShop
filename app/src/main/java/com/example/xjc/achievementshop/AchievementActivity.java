package com.example.xjc.achievementshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by XJC on 2015/8/31.
 */
public class AchievementActivity extends Activity {

    private Button change;
    private Button before;
    private AchievementDB achievementDB;
    private SQLiteDatabase dbReader;
    private ListView listview;
    private TextView sum;
    private TextView title;
    private Cursor cursor;
    int index=0,sumPoint=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);
        SharedPreferences pref = getSharedPreferences("data",
                MODE_PRIVATE);
        sumPoint = pref.getInt("sumPoint", 0);
        achievementDB = new AchievementDB(this);
        dbReader = achievementDB.getReadableDatabase();
        listview =(ListView)findViewById(R.id.List_achievement);
        title=(TextView) findViewById(R.id.titleText);
        title.setText("成就库");
        sum = (TextView)findViewById(R.id.text_point);
        sum.setText("你拥有的成就值:"+sumPoint);
        before = (Button)findViewById(R.id.before);
        before.setText("返回");
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });
        change = (Button)findViewById(R.id.next);
        change.setText("添加");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(AchievementActivity.this,Achievement_add.class);
                startActivity(intent1);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(AchievementActivity.this);
                dialog1.setTitle("你确定完成该项了么?");
                dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cursor = dbReader.query(AchievementDB.TABLE_NAME1, null, null, null, null, null, null);
                        cursor.moveToPosition(index);
                        String thingPoint = cursor.getString(cursor.getColumnIndex(AchievementDB.Ach_point));
                        sumPoint+=Integer.parseInt(thingPoint);
                        sum.setText("你拥有的成就值:"+sumPoint);
                    }
                });
                dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog1.show();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(AchievementActivity.this);
                dialog2.setTitle("你确定删除该项么?");
                dialog2.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cursor = dbReader.query(AchievementDB.TABLE_NAME1, null, null, null, null, null, null);
                        cursor.moveToPosition(index);
                        String delID = cursor.getString(cursor.getColumnIndex(AchievementDB.ID));
                        dbReader.execSQL("DELETE FROM " + AchievementDB.TABLE_NAME1 + " WHERE _id="
                                + delID);
                        achSelectDB();
                    }
                });
                dialog2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog2.show();
                return false;
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
        SharedPreferences.Editor editor = getSharedPreferences("data",
                MODE_PRIVATE).edit();
        editor.putInt("sumPoint", sumPoint);
        editor.apply();
    }



}
