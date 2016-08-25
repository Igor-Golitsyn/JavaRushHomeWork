package com.javarush.test.level15.lesson12.home04;
/**
 * Created by polus on 30.06.2015.
 */
public class Moon implements Planet {
    private Moon(){}
    private static Moon moon=null;
    public static Moon getInstance (){
        if (moon==null)moon=new Moon();
        return moon;
    }
}
