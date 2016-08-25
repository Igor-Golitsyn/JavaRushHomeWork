package com.javarush.test.level14.lesson06.home01;
/**
 * Created by polus on 23.06.2015.
 */
public class BelarusianHen extends Hen {
    BelarusianHen(){}
    @Override
    int getCountOfEggsPerMonth() {
        return 40;
    }
    @Override
    String getDescription() {
        String rech = super.getDescription() + " Моя страна - " + Country.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
        return rech;
    }
}
