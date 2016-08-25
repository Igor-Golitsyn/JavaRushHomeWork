package com.javarush.test.level15.lesson12.home04;
/**
 * Created by polus on 30.06.2015.
 */
public class Sun implements Planet {
    private Sun(){}
    private static Sun sun=null;
    public static Sun getInstance(){
        if (sun==null)sun=new Sun();
        return sun;
    }
}
