package com.example.ourchemi.models;

import com.example.ourchemi.Constant;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateObj implements Comparable<DateObj> {
    int year;
    int month;
    int day;

    public void init(){
        year = month = day = Constant.NOK;
    }
    public DateObj(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateObj()
    {
        year = month = day = Constant.NOK;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DateObj(int month, int day) {
        this.month = month;
        this.day = day;
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

    @Override
    public int compareTo(DateObj dateObj) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate dt1 = LocalDate.of(year, month, day);
        LocalDate dt2 = LocalDate.of(year, dateObj.month, dateObj.day);
        return dt1.compareTo(dt2);
    }

    @Override
    public String toString() {
        return String.format("%d%02d%02d", year, month, day);
    }
}
