package com.example.yutong.association;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yutong on 2016/2/11.
 */
public class MyUser extends BmobUser {
    private Boolean sex;
    private String avaterPath;
    private BmobFile avater;

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAvaterPath(){
        return this.avaterPath;
    }

    public void setAvaterPath(String avaterPath){
        this.avaterPath = avaterPath;
    }

    public BmobFile getAvater(){
        return avater;
    }

    public void setAvater(BmobFile avater){
        this.avater = avater;
    }

}
