package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader fileReader=new BufferedReader(new FileReader(args[0]));
        Map<String,Double> map=new HashMap<>();
        String []splitLine;
        Double temp;
        Double max=0.0;
        Double spl;
        while (fileReader.ready()){
            splitLine=null;
            temp=0.0;
            splitLine=fileReader.readLine().split(" ");
            spl=Double.parseDouble(splitLine[1]);
            if (map.containsKey(splitLine[0])){
                temp=map.get(splitLine[0])+spl;
                map.put(splitLine[0],temp);
            } else map.put(splitLine[0],spl);
            if (max<spl)max=spl;
            if (max<temp)max=temp;
        }
        fileReader.close();
        for (String tt:map.keySet()){
            if (map.get(tt).equals(max)) System.out.print(tt + " ");
        }
    }
}
