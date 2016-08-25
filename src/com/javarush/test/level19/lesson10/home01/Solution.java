package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader fileReader=new BufferedReader(new FileReader(args[0]));
        Map<String,Double> map=new TreeMap<>();
        String []splitLine;
        Double temp;
        while (fileReader.ready()){
            splitLine=null;
            temp=0.0;
            splitLine=fileReader.readLine().split(" ");
            if (map.containsKey(splitLine[0])){
                temp=map.get(splitLine[0])+Double.parseDouble(splitLine[1]);
                map.put(splitLine[0],temp);
            } else map.put(splitLine[0],Double.parseDouble(splitLine[1]));
        }
        fileReader.close();
        for (String tt:map.keySet()){
            System.out.println(tt+" "+map.get(tt));
        }
    }
}
