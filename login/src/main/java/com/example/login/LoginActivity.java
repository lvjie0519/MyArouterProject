package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.router.BindPath;
import com.example.router.MyRouter;

@BindPath("login/LoginActivity")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openStudyActivity(View view){
        MyRouter.getInstance().jumpActivity("study/StudyActivity", null);
    }
}
