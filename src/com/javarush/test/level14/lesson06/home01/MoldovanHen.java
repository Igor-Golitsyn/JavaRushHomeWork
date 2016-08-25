package com.javarush.test.level14.lesson06.home01;
/**
 * Created by polus on 23.06.2015.
 */
public class MoldovanHen extends Hen {
    MoldovanHen(){}
    @Override
    int getCountOfEggsPerMonth() {
        return 30;
    }
    @Override
    String getDescription() {
        String rech = super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
        return rech;
    }
}
