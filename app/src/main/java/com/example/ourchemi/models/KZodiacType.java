package com.example.ourchemi.models;

public enum KZodiacType {
    MOUSE("자"), COW("축"), TIGER("인"), RABBIT("묘"),
    DRAGON("진"), SNAKE("사"), HORSE("오"), SHEEP("미"),
    MONKEY("신"), CHICKEN("유"), DOG("술"), PIG("해");

    private String name;

    KZodiacType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
