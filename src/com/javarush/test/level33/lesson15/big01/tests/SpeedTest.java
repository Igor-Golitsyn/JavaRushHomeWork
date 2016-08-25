package com.javarush.test.level33.lesson15.big01.tests;
import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Игорь on 06.05.2016.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String line : strings) {
            ids.add(shortener.getId(line));
        }
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (long key : ids) {
            strings.add(shortener.getString(key));
        }
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> keys = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        long timeShotener1 = getTimeForGettingIds(shortener1, origStrings, keys);
        keys = new HashSet<>();
        long timeShotener2 = getTimeForGettingIds(shortener2, origStrings, keys);
        Assert.assertTrue(timeShotener1 > timeShotener2);
        origStrings = new HashSet<>();
        timeShotener1 = getTimeForGettingStrings(shortener1, keys, origStrings);
        origStrings=new HashSet<>();
        timeShotener2=getTimeForGettingStrings(shortener2,keys,origStrings);
        Assert.assertEquals(timeShotener1,timeShotener2,20);
    }
}
