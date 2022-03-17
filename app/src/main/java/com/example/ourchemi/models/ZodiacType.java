package com.example.ourchemi.models;

public enum ZodiacType {
    ARIES("ARIES", 0), TAURUS("TAURUS",1),
    GEMINI("GEMINI",2), CANCER("CANCER",3),
    LEO("LEO",4), VIRGO("VIRGO",5),
    LIBRA("LIBRA",6), SCORPIO("SCORPIO",7),
    SAGITTARIUS("SAGITTARIUS",8), CARPRICORN("CARPRICORN",9),
    AQUARIUS("AQUARIUS",10), PISCES("PISCES",11);

    private String name;
    private Integer value;

    ZodiacType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }
    public Integer getValue()
    {
        return value;
    }
}
