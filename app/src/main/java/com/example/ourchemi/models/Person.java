package com.example.ourchemi.models;

import java.io.Serializable;

public class Person implements Serializable {
    public Person(){
        year = month = day = 0;
        mbti = "";
        completeInfo = false;
    }

    public int year;
    public int month;
    public int day;
    public String mbti;
    public String zodiacSign;
    public String kZodiacSign;
    public String birthStone;
    public String birthFlower;
    public boolean completeInfo;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getkZodiacSign() {
        return kZodiacSign;
    }

    public void setkZodiacSign(String kZodiacSign) {
        this.kZodiacSign = kZodiacSign;
    }

    public String getBirthStone() {
        return birthStone;
    }

    public void setBirthStone(String birthStone) {
        this.birthStone = birthStone;
    }

    public String getBirthFlower() {
        return birthFlower;
    }

    public void setBirthFlower(String birthFlower) {
        this.birthFlower = birthFlower;
    }

    public boolean isCompleteInfo() {
        return completeInfo;
    }

    public void setCompleteInfo(boolean completeInfo) {
        this.completeInfo = completeInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", mbti='" + mbti + '\'' +
                ", zodiacSign='" + zodiacSign + '\'' +
                ", kZodiacSign='" + kZodiacSign + '\'' +
                ", birthStone='" + birthStone + '\'' +
                ", birthFlower='" + birthFlower + '\'' +
                ", completeInfo=" + completeInfo +
                '}';
    }
}
