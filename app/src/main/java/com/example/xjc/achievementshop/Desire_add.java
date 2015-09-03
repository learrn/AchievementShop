package com.example.xjc.achievementshop;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by XJC on 2015/9/3.
 */
public class Desire_add extends Activity implements View.OnClickListener{
    private Button add,cancel;
    private EditText thing,point;
    private AchievementDB achievementDB;
    private SQLiteDatabase dbWriter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desire_add);
        add = (Button)findViewById(R.id.sure_add);
        cancel = (Button)findViewById(R.id.cancel_add);
        thing = (EditText) findViewById(R.id.thing_add);
        point=(EditText) findViewById(R.id.point_add);
        achievementDB = new AchievementDB(this);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dbWriter = achievementDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_add:
                desAddDB();
                finish();
                break;
            case R.id.cancel_add:
                finish();
                break;
        }

    }
    public void desAddDB(){
        ContentValues cv =new ContentValues();
        cv.put(AchievementDB.Des_thing,thing.getText().toString());
        cv.put(AchievementDB.Des_point,point.getText().toString());
        dbWriter.insert(AchievementDB.TABLE_NAME2, null, cv);
    }
}
