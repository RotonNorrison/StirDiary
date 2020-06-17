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
        int brandy_vol = diary.getWinelist().get(0).getVolume();
        int gin_vol = diary.getWinelist().get(1).getVolume();
        int rum_vol = diary.getWinelist().get(2).getVolume();
        int tequila_vol = diary.getWinelist().get(3).getVolume();
        int vodka_vol = diary.getWinelist().get(4).getVolume();
        int whisky_vol = diary.getWinelist().get(5).getVolume();
        int grenadine_vol = diary.getWinelist().get(6).getVolume();
        int orange_vol = diary.getWinelist().get(7).getVolume();
        int cherry_vol = diary.getWinelist().get(8).getVolume();
        int lime_vol = diary.getWinelist().get(9).getVolume();
        int triplesec_vol = diary.getWinelist().get(10).getVolume();
        int blackberry_vol = diary.getWinelist().get(11).getVolume();
        int sentiment=diary.getSentiment();
        String date = diary.getDate();
        insert(id, title, content, bottle, share, brandy_vol, gin_vol, rum_vol, tequila_vol, vodka_vol, whisky_vol, grenadine_vol, orange_vol, cherry_vol, lime_vol, triplesec_vol, blackberry_vol, stir, date,sentiment);
    }

    public static void insert(String id, String title, String content, int bottle, int share, int brandy_vol, int gin_vol, int rum_vol, int tequila_vol, int vodka_vol, int whisky_vol, int grenadine_vol, int orange_vol, int cherry_vol, int lime_vol, int triplesec_vol, int blackberry_vol, int stir, String date,int sentiment) {
        db = ms.getWritableDatabase();

        //db.execSQL("insert into diary(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol,grenadine_vol,orange_vol,cherry_vol,lime_vol,triplesec_vol,blackberry_vol,stir,date)" +
        //        "values('"+id+"','"+title+"','"+content+"','"+bottle+"','"+share+"','"+brandy_vol+"','"+gin_vol+"','"+rum_vol+"','"+tequila_vol+"','"+vodka_vol+"','"+whisky_vol+"','"+grenadine_vol+"','"+orange_vol+"','"+cherry_vol+"','"+lime_vol+"','"+triplesec_vol+"','"+blackberry_vol+"','"+stir+"','"+date+"'"+");");
        String sql = "insert into diary" +
                "(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol," +
                "grenadine_vol,orange_vol,cherry_vol,lime_vol,triplesec_vol,blackberry_vol,stir,date,sentiment) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] args = {id, title, content, bottle, share, brandy_vol, gin_vol, rum_vol, tequila_vol, vodka_vol, whisky_vol,
                grenadine_vol, orange_vol, cherry_vol, lime_vol, triplesec_vol, blackberry_vol, stir, date,sentiment};
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
        while (c.moveToNext()){
            Diary diary=new Diary();
            diary.setUid(c.getString(c.getColumnIndex("diaryid")));
            diary.setDiary_title(c.getString(c.getColumnIndex("title")));
            diary.setText(c.getString(c.getColumnIndex("content")));
            diary.setBottle_kind(c.getColumnIndex("bottle"));
            diary.setWineVol(0,c.getInt(c.getColumnIndex("brandy_vol")));
            diary.setWineVol(1,c.getInt(c.getColumnIndex("gin_vol")));
            diary.setWineVol(2,c.getInt(c.getColumnIndex("rum_vol")));
            diary.setWineVol(3,c.getInt(c.getColumnIndex("tequila_vol")));
            diary.setWineVol(4,c.getInt(c.getColumnIndex("vodka_vol")));
            diary.setWineVol(5,c.getInt(c.getColumnIndex("whisky_vol")));
            diary.setWineVol(6,c.getInt(c.getColumnIndex("grenadine_vol")));
            diary.setWineVol(7,c.getInt(c.getColumnIndex("orange_vol")));
            diary.setWineVol(8,c.getInt(c.getColumnIndex("cherry_vol")));
            diary.setWineVol(9,c.getInt(c.getColumnIndex("lime_vol")));
            diary.setWineVol(10,c.getInt(c.getColumnIndex("triplesec_vol")));
            diary.setWineVol(11,c.getInt(c.getColumnIndex("blackberry_vol")));
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
