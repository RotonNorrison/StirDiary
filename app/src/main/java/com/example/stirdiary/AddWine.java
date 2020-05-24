package com.example.stirdiary;

import java.io.Serializable;

public class AddWine implements  Serializable{
    private String winename;
    private Double volume;

    public AddWine(String name, double vol) {
        winename=name;
        volume=vol;
    }

    public void setWinename(String name){
        winename=name;
    }

    public void setVolume(Double vol){
        volume=vol;
    }

    public String getWinename(){
        return winename;
    }

    public Double getVolume(){
        return volume;
    }
}
