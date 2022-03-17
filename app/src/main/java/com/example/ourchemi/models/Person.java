package com.example.ourchemi.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Serializable {

    private DateObj birthday;
    private DateObj lunarBirthday;
    private String ddi;
    private String gapja;
    private String mbti;
    private String zodiacSign;
    private String birthStone;
    private String birthFlower;
    private boolean completeInfo;

    public Person()
    {
        mbti = "";
        completeInfo = false;
        birthday = new DateObj();
        lunarBirthday = new DateObj();
        ddi = gapja = mbti = zodiacSign = "";
        birthStone = birthFlower = "";
        completeInfo = false;
    }

    public Person(DateObj birthday, DateObj lunarBirthday, String ddi, String gapja, String mbti, String zodiacSign, String birthStone, String birthFlower, boolean completeInfo) {
        this.birthday = birthday;
        this.lunarBirthday = lunarBirthday;
        this.ddi = ddi;
        this.gapja = gapja;
        this.mbti = mbti;
        this.zodiacSign = zodiacSign;
        this.birthStone = birthStone;
        this.birthFlower = birthFlower;
        this.completeInfo = completeInfo;
    }

    public DateObj getBirthday() {
        return birthday;
    }

    public void setBirthday(DateObj birthday) {
        this.birthday = birthday;
    }

    public DateObj getLunarBirthday() {
        return lunarBirthday;
    }

    public void setLunarBirthday(DateObj lunarBirthday) {
        this.lunarBirthday = lunarBirthday;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getGapja() {
        return gapja;
    }

    public void setGapja(String gapja) {
        this.gapja = gapja;
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

    public void copyByVal(Person p)
    {
        this.mbti            = p.getMbti();
        this.completeInfo    = p.isCompleteInfo();
        this.birthday        = p.getBirthday();
        this.lunarBirthday   = p.getLunarBirthday();
        this.ddi             = p.getDdi();
        this.gapja           = p.getGapja();
        this.mbti            = p.getMbti();
        this.zodiacSign      = p.getZodiacSign();
        this.birthStone      = p.getBirthStone();
        this.birthFlower     = p.getBirthFlower();
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthday=" + birthday +
                ", lunarBirthday=" + lunarBirthday +
                ", ddi='" + ddi + '\'' +
                ", gapja='" + gapja + '\'' +
                ", mbti='" + mbti + '\'' +
                ", zodiacSign='" + zodiacSign + '\'' +
                ", birthStone='" + birthStone + '\'' +
                ", birthFlower='" + birthFlower + '\'' +
                ", completeInfo=" + completeInfo +
                '}';
    }

}
