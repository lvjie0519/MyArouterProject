package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.router.BindPath;

@BindPath("study/StudyActivity")
public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
    }
}
