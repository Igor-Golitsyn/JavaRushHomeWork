package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Удалить всех людей, родившихся летом
 Создать словарь (Map<String, Date>) и занести в него десять записей по принципу:
 «фамилия» - «дата рождения».
 Удалить из словаря всех людей, родившихся летом.
 */
public class Solution {
    public static void main(String[] args) {
        HashMap<String, Date> base = new HashMap<>();
        base = createMap();
        removeAllSummerPeople(base);
    }
    public static HashMap<String, Date> createMap() {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Сталлоне", new Date("JANUARY 1 1980"));
        map.put("Igor", new Date("FEBRUARY 25 1979"));
        map.put("BB", new Date("MARCH 2 1950"));
        map.put("DD", new Date("APRIL 2 1981"));
        map.put("WW", new Date("MAY 15 1990"));
        map.put("WW1", new Date("JUNE 15 1991"));
        map.put("WW2", new Date("JULY 15 1990"));
        map.put("WW3", new Date("AUGUST 15 1990"));
        map.put("WW4", new Date("SEPTEMBER 6 1990"));
        map.put("WW5", new Date("NOVEMBER 15 1990"));
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        HashSet<String> temp = new HashSet<>();
        for (Map.Entry<String, Date> entrySet : map.entrySet()) {
            String key = entrySet.getKey();
            Date value = entrySet.getValue();
            if (value.getMonth() > 4 && value.getMonth() < 8) {
                temp.add(key);
            }
        }
        for (String temp1 : temp) {
            map.remove(temp1);
        }

    }

}
