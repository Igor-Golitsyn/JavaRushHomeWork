package com.javarush.test.level14.lesson08.bonus03;
/**
 * Created by polus on 26.06.2015.
 */
public class Singleton {
    private static Singleton singleton=new Singleton();
    private Singleton(){
    }
    static Singleton getInstance() {
        return singleton;
    }
}
