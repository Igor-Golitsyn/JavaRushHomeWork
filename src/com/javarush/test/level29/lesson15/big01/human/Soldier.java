package com.javarush.test.level29.lesson15.big01.human;
/**
 * Created by golit on 29.02.2016.
 */
public class Soldier extends Human {
    public Soldier(String name, int age) {
        super(name, age);
    }
    public void fight() {
    }
    @Override
    public void live() {
        fight();
    }
}
