package com.example.stirdiary;

import java.io.Serializable;

public class AddWine implements Serializable {
    private String winename;
    private int id;
    private int volume;
    public AddWine(int nid, int vol) {
        id = nid;
        volume = vol;
        switch (id) {
            case 0:
                winename = "brandy";
                break;
            case 1:
                winename = "gin";
                break;
            case 2:
                winename = "rum";
                break;
            case 3:
                winename = "tequila";
                break;
            case 4:
                winename = "vodka";
                break;
            case 5:
                winename = "whisky";
                break;
            case 6:
                winename = "grenadine";
                break;
            case 7:
                winename = "orange";
                break;
            case 8:
                winename = "cherry";
                break;
            case 9:
                winename = "lime";
                break;
            case 10:
                winename = "triplesec";
                break;
            case 11:
                winename = "blackberry";
                break;

        }

    }

    public void setWinename(String name){
        winename=name;
    }


    public void setVolume(int vol){
        volume=vol;

    }

    public String getWinename(){
        return winename;
    }

    public int getVolume() {
        return volume;
    }
}