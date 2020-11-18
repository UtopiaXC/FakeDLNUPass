package com.utopiaxc.fakedlnupass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.sql.PreparedStatement;

public class ActivityPass extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Pass);
        setContentView(R.layout.activity_pass);
        setCustomActionBar();

        TextView textViewNumber = findViewById(R.id.textViewNumber);
        textViewNumber.setText("学号："+variables.Number);

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText("姓名："+variables.Name);

        TextView textViewSex = findViewById(R.id.textViewSex);
        textViewSex.setText("性别："+variables.Sex);

        TextView textViewFaculty = findViewById(R.id.textViewFaculty);
        textViewFaculty.setText("院系："+variables.Faculty);

        TextView textViewClass = findViewById(R.id.textViewProfessionalClass);
        textViewClass.setText("专业班级："+variables.ProfessionalClass);

        TextView textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setText("有效期：\n"+variables.StartTime+" 至 "+variables.EndTime);

        Uri uri = Uri.fromFile(new File(variables.Photo));
        ImageView imageView=findViewById(R.id.imageView);
        imageView.setImageURI(uri);

        SharedPreferences sharedPreferences=getSharedPreferences("PersonalMessages",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Number",variables.Number);
        editor.putString("Name",variables.Name);
        editor.putString("Sex",variables.Sex);
        editor.putString("Faculty",variables.Faculty);
        editor.putString("ProfessionalClass",variables.ProfessionalClass);
        editor.putString("Photo",variables.Photo);
        editor.apply();
    }

    private void setCustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.actionbar_pass, null);
        actionBar.setCustomView(titleView, lp);

        actionBar.setDisplayShowHomeEnabled(false);//去掉导航
        actionBar.setDisplayShowTitleEnabled(false);//去掉标题
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);

        ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);

        imageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}