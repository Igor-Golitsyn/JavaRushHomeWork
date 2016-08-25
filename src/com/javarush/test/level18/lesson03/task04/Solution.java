package com.javarush.test.level18.lesson03.task04;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        Map<Integer, Integer> map = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        int min = 2147483647;
        int tempValue;
        while (fileInputStream.available() > 0) {
            int tempKey = fileInputStream.read();
            if (map.containsKey(tempKey)) {
                tempValue = map.get(tempKey);
                tempValue++;
                if (min > tempValue) min = tempValue;
                map.put(tempKey, tempValue);
            } else {
                map.put(tempKey, 1);
            }
            if (map.containsValue(1)) min = 1;
        }
        fileInputStream.close();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue().equals(min)) System.out.print(entry.getKey()+" ");
        }
    }
}
