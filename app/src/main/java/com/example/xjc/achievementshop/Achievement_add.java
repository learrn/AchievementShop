package com.example.xjc.achievementshop;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by XJC on 2015/9/3.
 */
public class Achievement_add extends Activity implements View.OnClickListener {

    private Button sureAdd, cancelAdd;
    private EditText thing, point;
    private AchievementDB achievementDB;
    private SQLiteDatabase dbWriter;
    private TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement_add);
        sureAdd = (Button) findViewById(R.id.next);
        cancelAdd = (Button) findViewById(R.id.before);
        thing = (EditText) findViewById(R.id.thing_add);
        point = (EditText) findViewById(R.id.point_add);
        title = (TextView) findViewById(R.id.titleText);
        title.setText("添加成就");
        sureAdd.setText("确定");
        cancelAdd.setText("返回");
        achievementDB = new AchievementDB(this);
        sureAdd.setOnClickListener(this);
        cancelAdd.setOnClickListener(this);
        dbWriter = achievementDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (thing.getText().toString().isEmpty()) {
                    Toast.makeText(Achievement_add.this, "任务不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (point.getText().toString().isEmpty()) {
                    Toast.makeText(Achievement_add.this, "分值不能为空",
                            Toast.LENGTH_SHORT).show();
                } else {
                    achAddDB();
                    finish();
                }
                break;
            case R.id.before:
                finish();
                break;
        }

    }

    public void achAddDB() {
        ContentValues cv = new ContentValues();
        cv.put(AchievementDB.Ach_thing, thing.getText().toString());
        cv.put(AchievementDB.Ach_point, point.getText().toString());
        dbWriter.insert(AchievementDB.TABLE_NAME1, null, cv);
    }
}
