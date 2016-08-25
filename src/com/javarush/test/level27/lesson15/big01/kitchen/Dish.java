package com.javarush.test.level27.lesson15.big01.kitchen;
/**
 * Created by Игорь on 25.12.2015.
 */
public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);
    private int duration;
    Dish(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }
    public static String allDishesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Dish.values().length; i++) {
            stringBuilder.append(Dish.values()[i]);
            if (i == Dish.values().length - 1) continue;
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
