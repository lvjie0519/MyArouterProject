package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.component.manager.ComponentManager;
import com.example.login.external.ILoginComponentApi;
import com.example.login.external.bean.UserInfo;
import com.example.router.BindPath;

@BindPath("study/StudyActivity")
public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
    }

    public void onClickGetUserInfo(View view){
        ILoginComponentApi loginComponentApi = ComponentManager.getInstance().getComponentApi(ILoginComponentApi.KEY);
        UserInfo userInfo = loginComponentApi.getUserInfo();

        TextView textView = findViewById(R.id.tv_show_user_info);
        textView.setText(userInfo.toString());
    }
}
