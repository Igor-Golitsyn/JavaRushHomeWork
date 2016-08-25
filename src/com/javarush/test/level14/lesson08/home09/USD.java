package com.javarush.test.level14.lesson08.home09;
/**
 * Created by polus on 25.06.2015.
 */
public class USD extends Money {
    public USD(double amount) {
        super(amount);
    }
    @Override
    public String getCurrencyName() {
        return "USD";
    }
}
