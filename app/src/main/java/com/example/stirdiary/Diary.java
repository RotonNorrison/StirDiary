package com.example.stirdiary;

import java.util.Dictionary;
import java.util.HashMap;

public class Diary {
    private String diary_title;
    private int bottle_kind;
    //基酒种类及对应的量
    private HashMap<Integer, Integer> wine_kind;
    private String text;
    private int decoration;
    private boolean share_state;


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

    public HashMap<Integer, Integer> getWine_kind() {
        return wine_kind;
    }

    public void setWine_kind(int wine_kind, int volume) {
        this.wine_kind.put(wine_kind, volume);
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
}
