package com.javarush.test.level33.lesson15.big01.tests;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by Игорь on 06.05.2016.
 */
public class FunctionalTest {
    public void testStorage(Shortener shortener){
        String str1="value01";
        String str2="value02";
        String str3="value01";
        long key1=shortener.getId(str1);
        long key2=shortener.getId(str2);
        long key3=shortener.getId(str3);
        Assert.assertNotEquals(key2,key1);
        Assert.assertNotEquals(key2,key3);
        Assert.assertEquals(key1,key3);
        String line1=shortener.getString(key1);
        String line2=shortener.getString(key2);
        String line3=shortener.getString(key3);
        Assert.assertEquals(line1,str1);
        Assert.assertEquals(line2,str2);
        Assert.assertEquals(line3,str3);
    }
    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy storageStrategy=new HashMapStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy storageStrategy=new OurHashMapStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy storageStrategy=new FileStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy storageStrategy=new HashBiMapStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy storageStrategy=new DualHashBidiMapStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy storageStrategy=new OurHashBiMapStorageStrategy();
        Shortener shortener=new Shortener(storageStrategy);
        testStorage(shortener);
    }
}
