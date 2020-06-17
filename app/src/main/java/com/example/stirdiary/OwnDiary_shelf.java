package com.example.stirdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class OwnDiary_shelf extends AppCompatActivity {
    private LinearLayout container;

    public void addWine() throws Exception {
        String imgsrc;
        final String contentsrc;
        DiaryFileHelper mDFH = new DiaryFileHelper(getApplicationContext());
        List<Diary> Dlist = null;
        int temp;
        try {
            Dlist = mDFH.readDiaryListFromFile("diary_list_storage");
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentsrc = "test";
        int num = Dlist.size();
        temp = Math.min(num, 3);
        num -= 3;
        for (int i = 0; i < temp; i++) {
            final Diary Diarytemp = Dlist.get(i);
            String name = Diarytemp.getDiary_title() + ".svg";

            DiaryThumbnailHelper mDTH = new DiaryThumbnailHelper(getApplicationContext());
            mDTH.generateDiarySVG(name, Diarytemp);

            FileInputStream input = getApplicationContext().openFileInput(name);
            SVG svg = SVG.getFromInputStream(input);

            container = findViewById(R.id.container2);

            SVGImageView child = new SVGImageView(this);
            child.setSVG(svg);
//            ImageView child = new ImageView(this);
//            child.setImageResource(R.drawable.ic_glass1);
//            child.setImageURI(Uri.parse(name));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 200);
            params.setMargins(5, 0, 5, 0);
            child.setLayoutParams(params);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(OwnDiary_shelf.this, DiaryReading.class);
                    it.putExtra("file", Diarytemp);
                    startActivity(it);
                }
            });
            container.addView(child);
        }
        temp = Math.min(num, 3);
        num -= 3;
        for (int i = 0; i < temp; i++) {
            final Diary Diarytemp = Dlist.get(i + 3);
            String name = Diarytemp.getDiary_title() + ".svg";

            DiaryThumbnailHelper mDTH = new DiaryThumbnailHelper(getApplicationContext());
            mDTH.generateDiarySVG(name, Diarytemp);

            FileInputStream input = getApplicationContext().openFileInput(name);
            SVG svg = SVG.getFromInputStream(input);

            container = findViewById(R.id.container2);

            SVGImageView child = new SVGImageView(this);
            child.setSVG(svg);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 200);
            params.setMargins(5, 0, 5, 0);
            child.setLayoutParams(params);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(OwnDiary_shelf.this, DiaryReading.class);
                    it.putExtra("file", Diarytemp);
                    startActivity(it);
                }
            });
            container.addView(child);
        }
        temp = Math.min(num, 3);
        num -= 3;
        for (int i = 0; i < temp; i++) {
            final Diary Diarytemp = Dlist.get(i + 6);
            String name = Diarytemp.getDiary_title() + ".svg";

            DiaryThumbnailHelper mDTH = new DiaryThumbnailHelper(getApplicationContext());
            mDTH.generateDiarySVG(name, Diarytemp);

            FileInputStream input = getApplicationContext().openFileInput(name);
            SVG svg = SVG.getFromInputStream(input);

            container = findViewById(R.id.container2);

            SVGImageView child = new SVGImageView(this);
            child.setSVG(svg);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 200);
            params.setMargins(5, 0, 5, 0);
            child.setLayoutParams(params);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(OwnDiary_shelf.this, DiaryReading.class);
                    it.putExtra("file", Diarytemp);
                    startActivity(it);
                }
            });
            container.addView(child);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.own_diary_shelf);
        try {
            addWine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
