package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
public class Solution {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Map<Integer,Integer> map=new TreeMap<>();
            while (fis.available()>0){
                int tempKey=fis.read();
                if (map.containsKey(tempKey)){
                    int tempValue=map.get(tempKey);
                    tempValue++;
                    map.put(tempKey,tempValue);
                }else {
                    map.put(tempKey,1);
                }
            }
            fis.close();
            Set set=map.entrySet();
            Iterator iterator=set.iterator();
            while (iterator.hasNext()){
                Map.Entry entry= (Map.Entry) iterator.next();
                char tempChar=(char)((int)(entry.getKey()));
                System.out.println(tempChar+" "+entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
