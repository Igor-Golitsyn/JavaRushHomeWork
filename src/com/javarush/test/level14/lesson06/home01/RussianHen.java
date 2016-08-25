package com.javarush.test.level14.lesson06.home01;
/**
 * Created by polus on 23.06.2015.
 */
public class RussianHen extends Hen {
    RussianHen(){}
    @Override
    int getCountOfEggsPerMonth() {
        return 10;
    }
    @Override
    String getDescription() {
        String rech = super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
        return rech;
    }
}
