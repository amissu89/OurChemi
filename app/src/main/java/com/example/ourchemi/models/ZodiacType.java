package com.example.ourchemi.models;

public enum ZodiacType {
    ARIES("ARIES"), TAURUS("TAURUS"),
    GEMINI("GEMINI"), CANCER("CANCER"),
    LEO("LEO"), VIRGO("VIRGO"),
    LIBRA("LIBRA"), SCORPIO("SCORPIO"),
    SAGITTARIUS("SAGITTARIUS"), CARPRICORN("CARPRICORN"),
    AQUARIUS("AQUARIUS"), PISCES("PISCES");

    private String name;

    ZodiacType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
