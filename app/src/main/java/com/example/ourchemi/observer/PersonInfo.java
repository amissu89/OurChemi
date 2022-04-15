package com.example.ourchemi.observer;

import com.example.ourchemi.models.Person;

public class PersonInfo implements Observer {

    private Person person;

    public PersonInfo(Person p) {
        person = p;
    }

    public String getName() {
        return person.getName();
    }

    @Override
    public void update(Person p) {
        if(person.getName().equals(p.getName())){
            person.copyByVal(p);
        } else {
            System.out.println("Name is different.");
        }
    }
}
