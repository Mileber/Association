package com.example.yutong.association;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Start extends AppCompatActivity {
    public final static String USER_NAME = "com.example.yutong.association.USERNAME";
    public final static String AVATER = "com.example.yutong.association.AVATER";
    private String bmobID="0b9f9edeb908b6ce176164a39198c0f3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // 初始化 Bmob SDK
        Bmob.initialize(this, bmobID);

        Context context = getApplicationContext();
        BmobUser bmobUser = BmobUser.getCurrentUser(context);
        if(bmobUser != null){
            // 允许用户使用应用
            Intent intent = new Intent(this, MainActivity.class);
            //BmobUser中的特定属性
            String username = (String) BmobUser.getObjectByKey(context, "username");
            String avater = (String)BmobUser.getObjectByKey(context,"avater");
            intent.putExtra(USER_NAME, username);
            intent.putExtra(AVATER,avater);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
