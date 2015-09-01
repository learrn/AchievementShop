package com.example.xjc.achievementshop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  {

    private TextView apptitle;
    private Button achievement;
    private Button desire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        achievement = (Button)findViewById(R.id.achievement);
        desire = (Button)findViewById(R.id.desire);
        apptitle = (TextView)findViewById(R.id.apptitle);
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(MainActivity.this,AchievementActivity.class);
                startActivity(intent1);
            }
        });
        desire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(MainActivity.this,DesireActivity.class);
                startActivity(intent2);
            }
        });
    }

}
