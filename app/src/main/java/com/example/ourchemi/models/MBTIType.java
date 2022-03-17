package com.example.ourchemi.models;

public enum MBTIType {
    INFP("INFP", 0), ENFP("ENFP",1), INFJ("INFJ",2),
    ENFJ("ENFJ", 3), INTJ("INTJ",4), ENTJ("ENTJ",5),
    INTP("INTP",6), ENTP("ENTP",7), ISFP("ISFP", 8),
    ESFP("ESFP",9), ISTP("ISTP",10), ESTP("ESTP",11),
    ISFJ("ISFJ",12), ESFJ("ESFJ",13), ISTJ("ISTJ",14),
    ESTJ("ESTJ",15), SIZE("SIZE",16);

    private final String name;
    private final int value;

    MBTIType(String str, int i) {
        this.name = str;
        this.value = i;
    }

    public String getName(){
        return name;
    }
    public int getValue(){

        return value;
    }
}
