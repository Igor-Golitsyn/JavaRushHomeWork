package com.javarush.test.level33.lesson15.big01;
import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by golit on 28.04.2016.
 */
public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> longSet = new HashSet<>();
        for (String line : strings) {
            longSet.add(shortener.getId(line));
        }
        return longSet;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long lLong : keys) {
            stringSet.add(shortener.getString(lLong));
        }
        return stringSet;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Shortener shortener = new Shortener(strategy);
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }
        Date start = new Date();
        Set<Long> iDs = getIds(shortener, stringSet);
        Date finish = new Date();
        Helper.printMessage(finish.getTime() - start.getTime() + "");
        start = new Date();
        Set<String> stringsFromID = getStrings(shortener, iDs);
        finish = new Date();
        Helper.printMessage(finish.getTime() - start.getTime() + "");
        if (stringSet.containsAll(stringsFromID) && stringsFromID.containsAll(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashMapStorageStrategy(), 10000L);
        //testStrategy(new FileStorageStrategy(),10000L);
        testStrategy(new OurHashBiMapStorageStrategy(),10000L);
        testStrategy(new HashBiMapStorageStrategy(),10000L);
        testStrategy(new DualHashBidiMapStorageStrategy(),10000L);
    }
}
