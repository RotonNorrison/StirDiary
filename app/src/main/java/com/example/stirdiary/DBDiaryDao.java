package com.example.stirdiary;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBDiaryDao {
    private static SQLiteDatabase db;
    private static DBHelper ms;
    private Diary diary;
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
        insert(id, title, content, bottle, share, 0, 0, 0, 0, 0, 0, stir);
    }

    public static void insert(String id, String title, String content, int bottle, int share, int brandy_vol, int gin_vol, int rum_vol, int tequila_vol, int vodka_vol, int whisky_vol, int stir) {
        db = ms.getWritableDatabase();

        db.execSQL("insert into diary(diaryid,title,content,bottle,share,brandy_vol,gin_vol,rum_vol,tequila_vol,vodka_vol,whisky_vol,stir)" +
                "values('" + id + "','" + title + "','" + content + "','" + bottle + "','" + share + "','" + brandy_vol + "','" + gin_vol + "','" + rum_vol + "','" + tequila_vol + "','" + vodka_vol + "','" + whisky_vol + "','" + stir + "');");
        System.out.println("one item inserted");
        db.close();
    }
}

