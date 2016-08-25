package com.javarush.test.level15.lesson12.bonus01;
/**
 * Created by polus on 02.07.2015.
 */
public class Plane implements Flyable {
    int passengers;
    public Plane(int passengers) {
        this.passengers=passengers;
    }
    @Override
    public void fly() {
    }
}
