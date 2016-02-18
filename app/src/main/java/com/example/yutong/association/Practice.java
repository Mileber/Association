package com.example.yutong.association;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yutong on 2016/2/13.
 */
public class Practice extends BmobObject{
    public String name;
    public String id;
    public Integer memberNum;
    public String style;
    public BmobFile logo;

    public Practice(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public Integer getMemberNum() {
        return memberNum;
    }
    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public BmobFile getLogo() {
        return logo;
    }
    public void setLogo(BmobFile logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return name;
    }
}
