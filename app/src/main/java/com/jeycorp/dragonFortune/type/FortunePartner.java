package com.jeycorp.dragonFortune.type;

public class FortunePartner {
    private String name; // 이름
    private String sex; // 성별 (남,여)
    private String solunar; // 양력/음력 (양력 ; // solar, 음력 ; // lunar)
    private String year; // 년. (네자리)
    private String month; // 월 (두자리)
    private String day; // 일 (두자리)
    private String hour; // 시간 (00 두자리로 표시 모르면 00으로)
    private String min; // 분 (00 두자리로 표시 모르면 00으로)

//    private String chineseZodiac;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSolunar() {
        return solunar;
    }

    public void setSolunar(String solunar) {
        this.solunar = solunar;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

//    public String getChineseZodiac() {
//        return chineseZodiac;
//    }
//
//    public void setChineseZodiac(String chineseZodiac) {
//        this.chineseZodiac = chineseZodiac;
//    }
}
