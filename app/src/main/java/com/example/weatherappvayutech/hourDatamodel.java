package com.example.weatherappvayutech;

public class hourDatamodel {
    int image;
    String temp,desc,time;

    public hourDatamodel(String time ,int image, String temp, String desc) {

        this.time = time;
        this.image = image;
        this.temp = temp;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
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
