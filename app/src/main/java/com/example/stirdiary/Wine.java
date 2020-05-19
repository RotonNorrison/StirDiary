package com.example.stirdiary;

public class Wine {
    private int number;
    private int volume;

    public Wine() {
    }

    ;

    public Wine(int num, int vol) {
        number = num;
        volume = vol;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
