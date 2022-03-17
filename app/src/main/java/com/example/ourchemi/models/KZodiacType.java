package com.example.ourchemi.models;

public enum KZodiacType {
    MOUSE("자",0), COW("축",1), TIGER("인",2),
    RABBIT("묘",3), DRAGON("진",4), SNAKE("사",5),
    HORSE("오",6), SHEEP("미",7), MONKEY("신",8),
    CHICKEN("유",9), DOG("술",10), PIG("해",11);

    private String name;
    private Integer value;

    KZodiacType(String name, Integer value){
        this.name = name;
        this.value = value;
    }
    public Integer getValue(){
        return value;
    }
    public String getName(){
        return name;
    }
}
