package com.example.xjc.achievementshop;

/**
 * Created by XJC on 2015/8/31.
 */
public class Desire {
    private String thing;
    private int buttonid;

    public Desire(String thing,int buttonid){
        this.thing = thing;
        this.buttonid= buttonid;
    }
    public String getThing(){
        return thing;
    }
    public int getButtonid(){
        return buttonid;
    }
}
