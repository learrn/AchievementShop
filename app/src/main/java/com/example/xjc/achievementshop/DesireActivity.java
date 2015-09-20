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
import android.widget.Toast;


/**
 * Created by XJC on 2015/8/31.
 */
public class DesireActivity extends Activity {

    private Button change;
    private Button before;
    private AchievementDB achievementDB;
    private SQLiteDatabase dbReader;
    private ListView listview;
    private TextView sum;
    private Cursor cursor;
    private int index=0,sumPoint=0;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desire);
        SharedPreferences pref = getSharedPreferences("data",
                MODE_PRIVATE);
        sumPoint = pref.getInt("sumPoint", 0);
        achievementDB = new AchievementDB(this);
        dbReader = achievementDB.getReadableDatabase();
        listview =(ListView)findViewById(R.id.List_desire);
        title=(TextView) findViewById(R.id.titleText);
        title.setText("欲望库");
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
                Intent intent1 =new Intent(DesireActivity.this,Desire_add.class);
                startActivity(intent1);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(DesireActivity.this);
                dialog1.setTitle("你确定兑换该项么?");
                dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cursor = dbReader.query(AchievementDB.TABLE_NAME2, null, null, null, null, null, null);
                        cursor.moveToPosition(index);
                        String thingPoint = cursor.getString(cursor.getColumnIndex(AchievementDB.Des_point));
                        if (sumPoint<Integer.parseInt(thingPoint)){
                            Toast.makeText(DesireActivity.this, "你的成就值不足",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            sumPoint -=Integer.parseInt(thingPoint);
                            sum.setText("你拥有的成就值:"+sumPoint);
                        }

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
                AlertDialog.Builder dialog = new AlertDialog.Builder(DesireActivity.this);
                dialog.setTitle("你确定删除该项么?");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cursor = dbReader.query(AchievementDB.TABLE_NAME2, null, null, null, null, null, null);
                        cursor.moveToPosition(index);
                        String delID = cursor.getString(cursor.getColumnIndex(AchievementDB.ID));
                        dbReader.execSQL("DELETE FROM " + AchievementDB.TABLE_NAME2 + " WHERE _id="
                                + delID);
                        desSelectDB();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                return false;
            }
        });
    }
    public void desSelectDB(){
        this.cursor=this.dbReader.query(AchievementDB.TABLE_NAME2,null,null,null,null,null,null);
        DesireAdapter desireAdapter = new DesireAdapter(this,this.cursor);
        this.listview.setAdapter(desireAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        desSelectDB();
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
