package com.example.ourchemi.models;

public enum FiveElement {
    WATER("수"), SOIL("토"), TREE("목"),
    FIRE("화"), GOLD("금");

    private String name;

    FiveElement(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
