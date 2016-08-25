package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
 Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
 Удалить людей, имеющих одинаковые имена.
 */
public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map = createMap();
        System.out.println(map);
        removeTheFirstNameDuplicates(map);
        System.out.println(map);
    }
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("QQQ", "zz");
        map.put("WWW", "xx");
        map.put("EEE", "zz");
        map.put("RRR", "cc");
        map.put("TTT", "ss");
        map.put("YYY", "xx");
        map.put("UUU", "zz");
        map.put("III", "mm");
        map.put("OOO", "nn");
        map.put("PPP", "bb");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        HashMap<String, String> temp = new HashMap<>(map);
        for (Map.Entry<String, String> entrySet : temp.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            for (Map.Entry<String, String> entrySet1 : temp.entrySet()) {
                String key1 = entrySet1.getKey();
                String value1 = entrySet1.getValue();
                if (value1.equals(value) && (!key1.equals(key))) {
                    removeItemFromMapByValue(map, value1);
                }
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }
}
