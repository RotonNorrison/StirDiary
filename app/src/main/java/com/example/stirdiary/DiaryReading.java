package com.example.stirdiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DiaryReading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_reading_page);

        //创建文件辅助类
        //获得日记内容
        Intent it = getIntent();
        final Diary myDiary = (Diary) it.getSerializableExtra("file");
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText(myDiary.getText());
        TextView title = findViewById(R.id.reading_title);
        title.setText(myDiary.getDiary_title());

        ImageView bottle = (ImageView) findViewById(R.id.reading_img);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialog = LayoutInflater.from(DiaryReading.this).inflate(R.layout.diary_reading_ingredients, null);
                TextView title = dialog.findViewById(R.id.ingredient_title);
                title.setText(myDiary.getDiary_title());
                TextView text = dialog.findViewById(R.id.ingredient_text);
                ArrayList<AddWine> winelist = myDiary.getWinelist();
                StringBuilder textStr = new StringBuilder();
                for(AddWine wine : winelist) {
                    if(textStr.length()>0) textStr.append("\n");
                    textStr.append(String.format("%s %.1fmL", wine.getWinename(), wine.getVolume() / 10.0));
                }
                text.setText(textStr.toString());
                AlertDialog alertDialog = new AlertDialog.Builder(DiaryReading.this)
                        .setTitle("Ingredients")
                        .setView(dialog)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface di, int i) {}
                        }).create();
                alertDialog.show();
            }
        });
    }
}