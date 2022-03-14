package com.example.ourchemi.models;

public class KZodiac {
    private String name;
    private boolean isMinus;
    private String type;

    public KZodiac(){
        name = "";
        isMinus = true;
        type = "";
    }
    public KZodiac(String name, boolean isMinus, String type) {
        this.name = name;
        this.isMinus = isMinus;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMinus() {
        return isMinus;
    }

    public void setMinus(boolean minus) {
        isMinus = minus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
