package com.example.ourchemi.models;

import com.example.ourchemi.Constant;

import java.io.Serializable;

public class Chemistry implements Serializable {
    private Person p1;
    private Person p2;
    private Integer mbtiScore;
    private Integer zodiacScore;
    private Integer ddiScore;
    private Double totalScore;

    public Chemistry() {
        p1 = new Person();
        p2 = new Person();
        mbtiScore = zodiacScore = ddiScore = 0;
        totalScore = 0.0;
    }

    public Chemistry(Person p1, Person p2, Integer mbtiScore, Integer zodiacScore, Integer ddiScore, Double totalScore) {
        this.p1 = new Person();
        this.p2 = new Person();
        this.p1.copyByVal(p1);
        this.p2.copyByVal(p2);
        this.mbtiScore      = mbtiScore;
        this.zodiacScore    = zodiacScore;
        this.ddiScore       = ddiScore;
        this.totalScore     = totalScore;
    }

    public Person getP1() {
        return p1;
    }

    public void setP1(Person p1) {
        this.p1.copyByVal(p1);
    }

    public Person getP2() {
        return p2;
    }

    public void setP2(Person p2) {
        this.p2.copyByVal(p2);
    }

    public Integer getMbtiScore() {
        return mbtiScore;
    }

    public void setMbtiScore(Integer mbtiScore) {
        this.mbtiScore = mbtiScore;
    }

    public Integer getZodiacScore() {
        return zodiacScore;
    }

    public void setZodiacScore(Integer zodiacScore) {
        this.zodiacScore = zodiacScore;
    }

    public Integer getDdiScore() {
        return ddiScore;
    }

    public void setDdiScore(Integer ddiScore) {
        this.ddiScore = ddiScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Chemistry{" +
                "p1=" + p1.toString() +
                ", p2=" + p2.toString() +
                ", mbtiScore=" + mbtiScore +
                ", zodiacScore=" + zodiacScore +
                ", ddiScore=" + ddiScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
