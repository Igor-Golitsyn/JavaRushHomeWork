package com.javarush.test.level15.lesson12.home04;
/**
 * Created by polus on 30.06.2015.
 */
public class Earth implements Planet {
    private Earth(){}
    private static Earth earth=null;
    public static Earth getInstance(){
        if (earth==null)earth=new Earth();
        return earth;
    }
}
