package com.example.xjc.achievementshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity  {

    private Button achievement;
    private Button desire;
    private Button help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        achievement = (Button)findViewById(R.id.achievement);
        desire = (Button)findViewById(R.id.desire);
        help = (Button)findViewById(R.id.but_help);
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
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder
                        (MainActivity.this);
                dialog.setTitle("使用说明");
                dialog.setMessage("1.成就库用于添加你计划完成的任务，以及完成时获得的成就值；欲望库用于添加想要得到的奖励，以及获得奖励所需成就值。\n" +
                        "2.在各个库右上角点击添加按钮即可进入添加界面添加。\n" +
                        "3.单击任务确定完成任务；长按任务可以删除任务。");
                dialog.setPositiveButton("好的", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });
    }

}
