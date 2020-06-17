package com.example.stirdiary;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBDiaryDao {
    private static SQLiteDatabase db;
    private static DBHelper ms;

    public DBDiaryDao(Context context) {
        super();
        ms = new DBHelper(context);
    }

    public static void insert(Diary diary) {
        String id = diary.getUid();
        String title = diary.getDiary_title();
        String content = diary.getText();
        int bottle = diary.getBottle_kind();
        int share = diary.isShare_state();
        int stir = diary.getStirWay();
        //XXX
        int brandy_vol = 0;
        int gin_vol = 0;
        int rum_vol = 0;
        int tequila_vol = 0;
        int vodka_vol = 0;
        int whisky_vol = 0;
        int grenadine_vol = 0;
        int orange_vol = 0;
        int cherry_vol = 0;
        int lime_vol = 0;
        int triplesec_vol = 0;
        int blackberry_vol = 0;
        for (int i = 0; i < diary.getWinelist().size(); i++) {
            String winename = diary.getWinelist().get(i).getWinename();
            int vol = diary.getWinelist().get(i).getVolume();
            switch (winename) {
                case "brandy":
                    brandy_vol += vol;
                    break;
                case "gin":
                    gin_vol += vol;
                    break;
                case "rum":
                    rum_vol += vol;
                    break;
                case "tequila":
                    tequila_vol += vol;
                    break;
                case "vodka":
                    vodka_vol += vol;
                    break;
                case "whisky":
                    whisky_vol += vol;
                    break;
                case "grenadine":
                    grenadine_vol += vol;
                    break;
                case "orange":
                    orange_vol += vol;
                    break;
                case "cherry":
                    cherry_vol += vol;
                    break;
                case "lime":
                    lime_vol += vol;
                    break;
                case "triplesec":
                    triplesec_vol += vol;
                    break;
                case "blackberry":
                    blackberry_vol += vol;
                    break;
            }
        }

        int sentiment = diary.getSentiment();
        String date = diary.getDate();
        insert(id, title, content, bottle, share, brandy_vol, gin_vol, rum_vol, tequila_vol, vodka_vol, whisky_vol, grenadine_vol, orange_vol, cherry_vol, lime_vol, triplesec_vol, blackberry_vol, stir, date, sentiment);
    }

    public static void insert(String id, String title, String content, int bottle, int share, int brandy_vol, int gin_vol, int rum_vol, int tequila_vol, int vodka_vol, int whisky_vol, int grenadine_vol, int orange_vol, int cherry_vol, int lime_vol, int triplesec_vol, int blackberry_vol, int stir, String date, int sentiment) {
        db = ms.getWritableDatabase();

        //db.execSQL("insert into diary(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol,grenadine_vol,orange_vol,cherry_vol,lime_vol,triplesec_vol,blackberry_vol,stir,date)" +
        //        "values('"+id+"','"+title+"','"+content+"','"+bottle+"','"+share+"','"+brandy_vol+"','"+gin_vol+"','"+rum_vol+"','"+tequila_vol+"','"+vodka_vol+"','"+whisky_vol+"','"+grenadine_vol+"','"+orange_vol+"','"+cherry_vol+"','"+lime_vol+"','"+triplesec_vol+"','"+blackberry_vol+"','"+stir+"','"+date+"'"+");");
        String sql = "insert into diary" +
                "(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol," +
                "grenadine_vol,orange_vol,cherry_vol,lime_vol,triplesec_vol,blackberry_vol,stir,date,sentiment) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] args = {id, title, content, bottle, share, brandy_vol, gin_vol, rum_vol, tequila_vol, vodka_vol, whisky_vol,
                grenadine_vol, orange_vol, cherry_vol, lime_vol, triplesec_vol, blackberry_vol, stir, date, sentiment};
        db.execSQL(sql, args);
        System.out.println("one item inserted");
        db.close();
    }

    public static ArrayList<Diary> selectBetweenDate(String start, String end) {
        db = ms.getReadableDatabase();
        String sql = "select * from diary where date >= ? and date <= ?";
        String[] args = {start, end};
        Cursor c = db.rawQuery(sql, args);
        ArrayList<Diary> diaries = new ArrayList<>();
        while (c.moveToNext()) {
            Diary diary = new Diary();
            diary.setUid(c.getString(c.getColumnIndex("diaryid")));
            diary.setDiary_title(c.getString(c.getColumnIndex("title")));
            diary.setText(c.getString(c.getColumnIndex("content")));
            diary.setBottle_kind(c.getColumnIndex("bottle"));
            diary.addWine(0, c.getInt(c.getColumnIndex("brandy_vol")));
            diary.addWine(1, c.getInt(c.getColumnIndex("gin_vol")));
            diary.addWine(2, c.getInt(c.getColumnIndex("rum_vol")));
            diary.addWine(3, c.getInt(c.getColumnIndex("tequila_vol")));
            diary.addWine(4, c.getInt(c.getColumnIndex("vodka_vol")));
            diary.addWine(5, c.getInt(c.getColumnIndex("whisky_vol")));
            diary.addWine(6, c.getInt(c.getColumnIndex("grenadine_vol")));
            diary.addWine(7, c.getInt(c.getColumnIndex("orange_vol")));
            diary.addWine(8, c.getInt(c.getColumnIndex("cherry_vol")));
            diary.addWine(9, c.getInt(c.getColumnIndex("lime_vol")));
            diary.addWine(10, c.getInt(c.getColumnIndex("triplesec_vol")));
            diary.addWine(11, c.getInt(c.getColumnIndex("blackberry_vol")));
            diary.setStirWay(c.getInt(c.getColumnIndex("stir")));
            diary.setDate(c.getString(c.getColumnIndex("date")));
            diary.setSentiment(c.getInt(c.getColumnIndex("sentiment")));
            diaries.add(diary);
        }
        c.close();
        db.close();
        return diaries;
    }
}

