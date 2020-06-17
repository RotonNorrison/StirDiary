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
        int sentiment=diary.getSentiment();
        String date = diary.getDate();
        insert(id, title, content, bottle, share, stir, date,sentiment);
    }

    public static void insert(String id, String title, String content, int bottle, int share,int stir, String date,int sentiment) {
        db = ms.getWritableDatabase();

        //db.execSQL("insert into diary(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol,grenadine_vol,orange_vol,cherry_vol,lime_vol,triplesec_vol,blackberry_vol,stir,date)" +
        //        "values('"+id+"','"+title+"','"+content+"','"+bottle+"','"+share+"','"+brandy_vol+"','"+gin_vol+"','"+rum_vol+"','"+tequila_vol+"','"+vodka_vol+"','"+whisky_vol+"','"+grenadine_vol+"','"+orange_vol+"','"+cherry_vol+"','"+lime_vol+"','"+triplesec_vol+"','"+blackberry_vol+"','"+stir+"','"+date+"'"+");");
        String sql = "insert into diary" +
                "(diaryid,title,content,bottle,share,stir,date,sentiment) " +
                "values(?,?,?,?,?,?,?,?)";
        Object[] args = {id, title, content, bottle, share, stir, date,sentiment};
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
