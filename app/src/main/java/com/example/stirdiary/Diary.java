package com.example.stirdiary;

import android.util.Pair;

import java.io.Serializable;
import java.util.List;

public class Diary implements Serializable {
    private String diary_title;
    private int bottle_kind;
    //基酒种类及对应的量
    private List<Pair<String, Double>> winelist;
    private String text;
    private int decoration;
    private boolean share_state;
    private int stir_way;


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


    public List<Pair<String, Double>> getWinelist() {
        return winelist;
    }

    public void addWine(String wine_name, double volume) {
        Pair<String, Double> temp = new Pair<String, Double>(wine_name, volume);
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

    public boolean isShare_state() {
        return share_state;
    }

    public void setShare_state(boolean share_state) {
        this.share_state = share_state;
    }

    public int getStirWay() {
        return stir_way;
    }

    public void setStirWay(int stir_way) {
        this.stir_way = stir_way;
    }

    public void showInfo() {
        System.out.println("Title: " + getDiary_title());
        System.out.println("Bottle: " + getBottle_kind());
        System.out.println("Text: " + getText());
        System.out.println("Decoration: " + getDecoration());
        System.out.println("ShareState: " + isShare_state());
        System.out.println("StirWay: " + getStirWay());
    }
}
