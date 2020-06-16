package com.example.stirdiary;

import android.util.Pair;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.example.stirdiary.UUIDGenerator;

public class Diary implements Serializable {
    private String uid;
    private String diary_title;
    private int bottle_kind;
    //基酒种类及对应的量
    private ArrayList<AddWine> winelist = new ArrayList<>();
    private String text;
    private int decoration;
    private int share_state;
    private int stir_way;

    public Diary(){
        uid=UUIDGenerator.getUUID();
        diary_title="";
        bottle_kind=0;
        winelist=new ArrayList<>();
        text="";
        share_state=0;
        stir_way=0;
    }
    public String getUid() { return uid; }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public int getBottle_kind() {
        return bottle_kind;
    }

    public void setBottle_kind(int bottle_kind) {
        this.bottle_kind = bottle_kind;
    }


    public ArrayList<AddWine> getWinelist() {
        return winelist;
    }

    public void addWine(String wine_name, double volume) {
        AddWine temp = new AddWine(wine_name, volume);
        winelist.add(temp);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDecoration() {
        return decoration;
    }

    public void setDecoration(int decoration) {
        this.decoration = decoration;
    }

    public int isShare_state() {
        return share_state;
    }

    public void setShare_state(int share_state) {
        this.share_state = share_state;
    }

    public int getStirWay() {
        return stir_way;
    }

    public void setStirWay(int stir_way) {
        this.stir_way = stir_way;
    }

    public void showInfo() {
        System.out.println("Uid: " + getUid());
        System.out.println("Title: " + getDiary_title());
        System.out.println("Bottle: " + getBottle_kind());
        System.out.println("Text: " + getText());
        System.out.println("Decoration: " + getDecoration());
        System.out.println("ShareState: " + isShare_state());
        System.out.println("StirWay: " + getStirWay());
        ArrayList<AddWine> list = getWinelist();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getWinename() + list.get(i).getVolume());
            }
        }

    }
}
