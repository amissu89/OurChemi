package com.example.ourchemi.observer;

import com.example.ourchemi.models.Person;

//추상화된 통보 대상 클래스
public interface Observer {
    //데이터 변경을 통보했을 때 처리하는 메소드
    void update(Person p);
}
