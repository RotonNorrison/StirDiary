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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.util.ArrayList;

public class DiaryReading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_reading_page);

        //获得日记内容
        Intent it = getIntent();
        final Diary myDiary = (Diary) it.getSerializableExtra("file");
        TextView paper = findViewById(R.id.diary_reading_page_textPaper);
        paper.setText(myDiary.getText());
        TextView title = findViewById(R.id.reading_title);
        title.setText(myDiary.getDiary_title());

        FileHelper input = new FileHelper(getApplicationContext());
        String imgname = null;
        try {
            imgname = input.read(myDiary.getUid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SVG svg = null;
        try {
            svg = SVG.getFromString(imgname);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        SVGImageView child = new SVGImageView(this);
        int id = 1000;
        child.setSVG(svg);
        child.setId(id);
        child.setScaleType(ImageView.ScaleType.FIT_END);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(300, 400);
        //put svg into the layout
        ConstraintLayout mView = findViewById(R.id.readinglayout);
        mView.addView(child, layoutParams);
        ConstraintSet mCset = new ConstraintSet();
        mCset.clone(mView);
        mCset.connect(1000, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 80);
        mCset.connect(1000, ConstraintSet.BOTTOM, R.id.diary_reading_page_textPaper, ConstraintSet.BOTTOM, 32);
        mCset.applyTo(mView);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialog = LayoutInflater.from(DiaryReading.this).inflate(R.layout.diary_reading_ingredients, null);
                TextView title = dialog.findViewById(R.id.ingredient_title);
                title.setText(myDiary.getDiary_title());
                TextView text = dialog.findViewById(R.id.ingredient_text);
                ArrayList<AddWine> winelist = myDiary.getWinelist();
                StringBuilder textStr = new StringBuilder();
                for (AddWine wine : winelist) {
                    if (textStr.length() > 0) textStr.append("\n");
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