package com.jeycorp.dragonFortune.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager2 {
    SharedPreferences Pref;
    SharedPreferences.Editor edit;
    Context mCon;


    public PreferenceManager2(Context context) {
        mCon = context;
        Pref = mCon.getSharedPreferences("DRAGONFORTUNE2", 0);
    }

    public boolean getSwitch(){
        Boolean isCheck =Pref.getBoolean("check",true);
        return isCheck;
    }
    public void setSwitch(Boolean isCheck){
        edit=Pref.edit();
        edit.putBoolean("check",isCheck);
        edit.commit();
    }


    public String getOneLine() {
        String name = Pref.getString("oneLine", null);
        return name;
    }
    public void setOneLine(String oneLine){
        edit = Pref.edit();
        edit.putString("oneLine",oneLine);
        edit.commit();
    }

    public String getOneLine2() {
        String name = Pref.getString("oneLine2", null);
        return name;
    }
    public void setOneLine2(String oneLine2){
        edit = Pref.edit();
        edit.putString("oneLine2",oneLine2);
        edit.commit();
    }



    //유저네임




    //유저네임
    public String getName() {
        String name = Pref.getString("name", null);
        return name;
    }
    public void setName(String name){
        edit = Pref.edit();
        edit.putString("name",name);
        edit.commit();
    }
    //성별
    public String getSex() {
        String sex = Pref.getString("sex", null);
        return sex;
    }
    public void setSex(String sex) {
        edit = Pref.edit();
        edit.putString("sex",sex);
        edit.commit();
    }
    //양력 음력
    public String getSolunar() {
        String solunar = Pref.getString("solunar", null);
        return solunar;
    }
    public void setSolunar(String solunar) {
        edit = Pref.edit();
        edit.putString("solunar",solunar);
        edit.commit();
    }
    //출생연도
    public String getYear() {
        String year = Pref.getString("year", null);
        return year;
    }
    public void setYear(String year) {
        edit = Pref.edit();
        edit.putString("year",year);
        edit.commit();
    }
    //출생 월
    public String getMonth() {
        String month  = Pref.getString("month", null);
        return month;
    }
    public void setMonth(String month) {
        edit = Pref.edit();
        edit.putString("month",month);
        edit.commit();
    }
    //태어난 일
    public String getDay() {
        String day  = Pref.getString("day", null);
        return day;
    }
    public void setDay(String day) {
        edit = Pref.edit();
        edit.putString("day",day);
        edit.commit();
    }
    //태어난 시
    public String getHour() {
        String hour = Pref.getString("hour", null);
        return hour;
    }
    public void setHour(String hour) {
        edit = Pref.edit();
        edit.putString("hour",hour);
        edit.commit();
    }
    //태어난 분
    public String getMin() {
        String min = Pref.getString("min", null);
        return min;
    }
    public void setMin(String min) {
        edit = Pref.edit();
        edit.putString("min",min);
        edit.commit();
    }
}
