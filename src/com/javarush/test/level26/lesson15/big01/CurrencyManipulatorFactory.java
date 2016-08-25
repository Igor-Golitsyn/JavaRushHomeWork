package com.javarush.test.level26.lesson15.big01;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Игорь on 15.12.2015.
 */
public class CurrencyManipulatorFactory {
    private static Set<CurrencyManipulator> manipulators = new HashSet<>();
    private CurrencyManipulatorFactory() { }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for (CurrencyManipulator manipulator : manipulators) {
            if (manipulator.getCurrencyCode().equals(currencyCode)) return manipulator;
        }
        CurrencyManipulator newManipulator = new CurrencyManipulator(currencyCode);
        manipulators.add(newManipulator);
        return newManipulator;
    }
    public static Collection getAllCurrencyManipulators(){
        return manipulators;
    }
}
