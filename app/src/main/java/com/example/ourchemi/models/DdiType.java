package com.example.ourchemi.models;

public enum DdiType {
    MOUSE("쥐"), COW("소"), TIGER("호랑이"), RABBIT("토끼"),
    DRAGON("용"), SNAKE("뱀"), HORSE("말"), SHEEP("양"),
    MONKEY("원숭이"), CHICKEN("닭"), DOG("개"), PIG("돼지");

    private String name;

    DdiType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "DdiType{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName(){
        return name;
    }
}
