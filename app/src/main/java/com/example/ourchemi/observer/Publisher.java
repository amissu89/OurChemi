package com.example.ourchemi.observer;

import com.example.ourchemi.models.Person;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Publisher{

    private List<Observer> observerList = new ArrayList<>();

    public void attach(Observer observer) {
        observerList.add(observer);
    }

    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObserverList(Person person) {
        for(Observer o : observerList)
        {
            o.update(person);
        }
    }
}
