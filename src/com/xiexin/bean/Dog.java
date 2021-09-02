package com.xiexin.bean;

public class Dog {
    private  int dogId;

    public int getDogId() {
        return dogId;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", dogSex='" + dogSex + '\'' +
                '}';
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getDogSex() {
        return dogSex;
    }

    public void setDogSex(String dogSex) {
        this.dogSex = dogSex;
    }

    private  String dogSex;

}
