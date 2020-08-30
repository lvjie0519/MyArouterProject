package com.example.myarouterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.router.MyRouter;


public class MainActivity extends AppCompatActivity {

    /**
     * LiveData  具有handler的能力， 并且能够防止内存泄漏问题
     */
    private MutableLiveData<String> mLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData(){
        mLiveData = new MutableLiveData<>();

        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("MainActivity", "onChanged s --->"+s);
            }
        });
    }

    public void openLoginActivity(View view) {
        MyRouter.getInstance().jumpActivity("login/LoginActivity", null);
    }

    public void onClickLivaData(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiveData.postValue("1111111111");
            }
        }).start();
    }
}
