package com.example.weatherappvayutech;

public class dailyDatamodel {
    int image;
    String date,day,temp,desc;

    public dailyDatamodel(int image, String date, String day ,String temp, String desc) {

        this.date = date;
        this.day = day;
        this.image = image;
        this.temp = temp;
        this.desc = desc;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
