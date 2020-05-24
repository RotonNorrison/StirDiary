package com.example.stirdiary;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

class DiaryList {
    private List<Diary> l;
    public DiaryList (List<Diary> _l) {
        l = _l;
    }
    public List<Diary> getList () {
        return l;
    }
}

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

    public void saveDiaryListToFile(String fileName, List<Diary> diary) throws Exception {
        FileHelper fh = new FileHelper(mContext);
        String diaryListJsonText = gson.toJson(new DiaryList(diary));
        fh.save(fileName, diaryListJsonText);
    }

    public List<Diary> readDiaryListFromFile(String fileName) throws IOException {
        FileHelper fh = new FileHelper(mContext);
        String diaryListJsonText = fh.read(fileName);
        DiaryList diaryList = gson.fromJson(diaryListJsonText, DiaryList.class);
        return diaryList.getList();
    }
}
