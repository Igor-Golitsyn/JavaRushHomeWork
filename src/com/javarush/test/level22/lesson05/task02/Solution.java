package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String str= null;
        try {
            int first=string.indexOf(9);
            int second=string.indexOf(9,first+1);
            str = string.substring(first+1,second);
        }
        catch (Exception e) {
            throw new TooShortStringException();
        }
        return str;
    }

    public static class TooShortStringException extends Exception {
    }
}
