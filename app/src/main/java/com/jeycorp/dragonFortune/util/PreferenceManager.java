package com.jeycorp.dragonFortune.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    SharedPreferences Pref;
    SharedPreferences.Editor edit;
    Context mCon;


    String yearComment;
    String tojungComment;
    String wealthComment;
    String compaComment;
    String ddiComment;
    String loveComment;
    String businessComment;
    String goldComment;



    public PreferenceManager(Context context) {
        mCon = context;
        Pref = mCon.getSharedPreferences("DRAGONFORTUNE", 0);
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

    public boolean getIsBack(){
        Boolean isBack =Pref.getBoolean("isBack",false);
        return isBack;
    }
    public void setIsBack(Boolean isBack){
        edit=Pref.edit();
        edit.putBoolean("isBack",isBack);
        edit.commit();
    }

    //앱버전
    public int getAppVersion() {
        int appVersion = Pref.getInt("appVersion", 0);
        return appVersion;
    }
    public void setAppVersion(int appVersion){
        edit = Pref.edit();
        edit.putInt("appVersion",appVersion);
        edit.commit();
    }

    //업뎃버전
    public int getUpVersion() {
        int upVersion = Pref.getInt("upVersion", 0);
        return upVersion;
    }
    public void setUpVersion(int upVersion){
        edit = Pref.edit();
        edit.putInt("upVersion",upVersion);
        edit.commit();
    }


    //초기화
    public String getRefresh() {
        String refresh = Pref.getString("refresh", "");
        return refresh;
    }
    public void setRefresh(String refresh){
        edit = Pref.edit();
        edit.putString("refresh",refresh);
        edit.commit();
    }



    //마켓주소
    public String getMUrl() {
        String mUrl = Pref.getString("mUrl", "");
        return mUrl;
    }
    public void setMUrl(String mUrl){
        edit = Pref.edit();
        edit.putString("mUrl",mUrl);
        edit.commit();
    }


//날짜
    public String getOneLine() {
        String name = Pref.getString("oneLine", "");
        return name;
    }
    public void setOneLine(String oneLine){
        edit = Pref.edit();
        edit.putString("oneLine",oneLine);
        edit.commit();
    }

    public String getOneLine2() {
        String name = Pref.getString("oneLine2", "");
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
        String name = Pref.getString("name", "");
        return name;
    }
    public void setName(String name){
        edit = Pref.edit();
        edit.putString("name",name);
        edit.commit();
    }
    //성별
    public String getSex() {
        String sex = Pref.getString("sex", "");
        return sex;
    }
    public void setSex(String sex) {
        edit = Pref.edit();
        edit.putString("sex",sex);
        edit.commit();
    }
    //양력 음력
    public String getSolunar() {
        String solunar = Pref.getString("solunar", "");
        return solunar;
    }
    public void setSolunar(String solunar) {
        edit = Pref.edit();
        edit.putString("solunar",solunar);
        edit.commit();
    }
    //출생연도
    public String getYear() {
        String year = Pref.getString("year", "");
        return year;
    }
    public void setYear(String year) {
        edit = Pref.edit();
        edit.putString("year",year);
        edit.commit();
    }
    //출생 월
    public String getMonth() {
        String month  = Pref.getString("month", "");
        return month;
    }
    public void setMonth(String month) {
        edit = Pref.edit();
        edit.putString("month",month);
        edit.commit();
    }
    //태어난 일
    public String getDay() {
        String day  = Pref.getString("day", "");
        return day;
    }
    public void setDay(String day) {
        edit = Pref.edit();
        edit.putString("day",day);
        edit.commit();
    }
    //태어난 시
    public String getHour() {
        String hour = Pref.getString("hour", "");
        return hour;
    }
    public void setHour(String hour) {
        edit = Pref.edit();
        edit.putString("hour",hour);
        edit.commit();
    }
    //태어난 분
    public String getMin() {
        String min = Pref.getString("min", "");
        return min;
    }
    public void setMin(String min) {
        edit = Pref.edit();
        edit.putString("min",min);
        edit.commit();
    }

    //코멘트 변경



    public String getStringCheck() {
        String stringCheck = Pref.getString("stringCheck", "");
        return stringCheck;
    }

    public void setStringCheck(String stringCheck) {
        edit = Pref.edit();
        edit.putString("stringCheck",stringCheck);
        edit.commit();

    }
}
