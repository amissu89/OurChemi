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

    @NonNull
    @Override
    public Person clone() {
        try{
            return (Person) super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private KZodiac kZodiac;
    private String birthStone;
    private String birthFlower;
    private boolean completeInfo;

    public Person(DateObj birthday, DateObj lunarBirthday, String ddi, String gapja, String mbti, String zodiacSign, KZodiac kZodiac, String birthStone, String birthFlower, boolean completeInfo) {
        this.birthday = birthday;
        this.lunarBirthday = lunarBirthday;
        this.ddi = ddi;
        this.gapja = gapja;
        this.mbti = mbti;
        this.zodiacSign = zodiacSign;
        this.kZodiac = kZodiac;
        this.birthStone = birthStone;
        this.birthFlower = birthFlower;
        this.completeInfo = completeInfo;
    }

    public Person(){
        mbti = "";
        completeInfo = false;
        birthday = new DateObj();
        lunarBirthday = new DateObj();
        ddi = gapja = mbti = zodiacSign = "";
        kZodiac = new KZodiac();
        birthStone = birthFlower = "";
        completeInfo = false;
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

    public KZodiac getkZodiac() {
        return kZodiac;
    }

    public void setkZodiac(KZodiac kZodiac) {
        this.kZodiac = kZodiac;
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

    public void copy(Person p){
        mbti            = p.getMbti();
        completeInfo    = p.isCompleteInfo();
        birthday    = p.getBirthday();
        lunarBirthday = p.getLunarBirthday();
        ddi = p.getDdi();
        gapja = p.getGapja();
        mbti = p.getMbti();
        zodiacSign = p.getZodiacSign();
        kZodiac = p.getkZodiac();
        birthStone = p.getBirthStone();
        birthFlower = p.getBirthFlower();
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
                ", kZodiac=" + kZodiac +
                ", birthStone='" + birthStone + '\'' +
                ", birthFlower='" + birthFlower + '\'' +
                ", completeInfo=" + completeInfo +
                '}';
    }

}
