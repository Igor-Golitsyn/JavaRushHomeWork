package com.javarush.test.level18.lesson03.task03;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* Самые частые байты
Ввести с консоли имя файла
Найти байты, которые чаше всех встречаются в файле
Вывести их на экран через пробел.
Закрыть поток ввода-вывода
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        int max = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<Integer, Integer> map = new HashMap<>();
        while (fileInputStream.available() > 0) {
            int temp = fileInputStream.read();
            if (map.containsKey(temp)) {
                int tempValue = map.get(temp);
                tempValue++;
                if (max < tempValue) max = tempValue;
                map.put(temp, tempValue);
            } else {
                map.put(temp, 1);
            }
        }
        fileInputStream.close();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (Integer.parseInt(String.valueOf(entry.getValue())) == max) System.out.print(entry.getKey() + " ");
        }
    }
}
