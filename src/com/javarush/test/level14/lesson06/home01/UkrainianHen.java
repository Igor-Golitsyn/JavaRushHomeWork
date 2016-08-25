package com.javarush.test.level14.lesson06.home01;
/**
 * Created by polus on 23.06.2015.
 */
public class UkrainianHen extends Hen {
    UkrainianHen(){}
    @Override
    int getCountOfEggsPerMonth() {
        return 20;
    }
    @Override
    String getDescription() {
        String rech = super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
        return rech;
    }
}
