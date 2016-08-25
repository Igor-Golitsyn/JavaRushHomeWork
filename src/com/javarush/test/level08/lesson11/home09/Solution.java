package com.javarush.test.level08.lesson11.home09;

        import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution {
    public static void main(String[] args) {
        String date = "JANUARY 1 2000";
        System.out.println(date + " = " + isDateOdd(date));
        date = "JANUARY 2 2020";
        System.out.println(date + " = " + isDateOdd(date));
    }

    public static boolean isDateOdd(String date) {
        Date d = new Date(date);
        Date zero = new Date(d.getYear(), 0, 1);
        long count = (d.getTime() - zero.getTime()) / 86400000;
        if (count % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
