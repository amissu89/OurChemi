package com.example.ourchemi.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateObj implements Comparable<DateObj> {
    int month;
    int day;

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
        System.out.println(dt1.toString() + " / " + dt2.toString());
        System.out.println("compare : " + dt1.compareTo(dt2));
        return dt1.compareTo(dt2);
    }
}
