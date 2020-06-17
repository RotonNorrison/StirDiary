package com.example.stirdiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.stirdiary.DBDiaryDao;
import com.example.stirdiary.UUIDGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DcFinal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diary_creation_final);

        final Diary creatingDiary = (Diary) getIntent().getSerializableExtra("diaryInfo");
        creatingDiary.showInfo();
        DiaryThumbnailHelper mDTH = new DiaryThumbnailHelper(getApplicationContext());
        try {
            mDTH.generateDiarySVG(creatingDiary.getUid(), creatingDiary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**  final DiaryFileHelper mDFH = new DiaryFileHelper(getApplicationContext());

         List<Diary> Dlist = null;
         try {
         Dlist = mDFH.readDiaryListFromFile("diary_list_storage");
         } catch (IOException e) {
         e.printStackTrace();
         }
         if (Dlist == null) {
         Dlist = new ArrayList<Diary>();
         }
         _________________________________________*/
        FileHelper input = new FileHelper(getApplicationContext());
        String imgname = null;
        try {
            imgname = input.read(creatingDiary.getUid());
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
        ConstraintLayout mView = findViewById(R.id.cons_view);
        mView.addView(child, layoutParams);
        ConstraintSet mCset = new ConstraintSet();
        mCset.clone(mView);
        mCset.connect(1000, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 80);
        mCset.connect(1000, ConstraintSet.BOTTOM, R.id.creatingFinal_sharebtn, ConstraintSet.TOP, 32);
        mCset.applyTo(mView);
        /**SVG svg=null;
         try {
         svg=SVG.getFromAsset(this.getAssets(),creatingDiary.getUid());
         } catch (SVGParseException e) {
         e.printStackTrace();
         } catch (IOException e) {
         e.printStackTrace();
         }
         if (svg.getDocumentWidth() != -1) {
         Bitmap newBM = Bitmap.createBitmap((int) Math.ceil(svg.getDocumentWidth()),
         (int) Math.ceil(svg.getDocumentHeight()),
         Bitmap.Config.ARGB_8888);
         Canvas bmcanvas = new Canvas(newBM);

         // Clear background to white
         bmcanvas.drawRGB(255, 255, 255);

         // Render our document onto our canvas
         svg.renderToCanvas(bmcanvas);
         }*/
        //分享按钮事件绑定
        ToggleButton sharebtn;
        sharebtn = findViewById(R.id.creatingFinal_sharebtn);
        sharebtn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    creatingDiary.setShare_state(1);
                else
                    creatingDiary.setShare_state(0);

            }
        });

        //下一步按钮事件绑定
        ImageView btn_for_end_creating;
        btn_for_end_creating = findViewById(R.id.creatingFinal_endbtn);
        /**final List<Diary> finalDlist = Dlist;*/
        btn_for_end_creating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本内容
                EditText text = (EditText) findViewById(R.id.creatingFinal_addTitle_editText);
                String title = text.getText().toString();
                creatingDiary.setDiary_title(title);
                creatingDiary.showInfo();

                DBDiaryDao dbDiaryDao=new DBDiaryDao(DcFinal.this);
                dbDiaryDao.insert(creatingDiary);
                finish();
            }
        });
    }
}

