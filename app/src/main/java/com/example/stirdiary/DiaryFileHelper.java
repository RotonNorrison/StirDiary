package com.example.stirdiary;

import android.content.Context;

import java.io.IOException;
import com.google.gson.Gson;

public class DiaryFileHelper {

    private Context mContext;

    private static Gson gson = new Gson();

    public DiaryFileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void saveDiaryToFile(String fileName, Diary diary) throws Exception {
        FileHelper fh = new FileHelper(mContext);
        String diaryJsonText = gson.toJson(diary);
        fh.save(fileName, diaryJsonText);
    }

    public Diary readDiaryFromFile(String fileName) throws IOException {
        FileHelper fh = new FileHelper(mContext);
        String diaryJsonText = fh.read(fileName);
        Diary diary = gson.fromJson(diaryJsonText, Diary.class);
        return diary;
    }
}
