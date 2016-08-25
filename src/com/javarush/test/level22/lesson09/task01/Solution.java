package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.*;
/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> arrayList=new ArrayList<>();
        while (bufferedReader.ready()) {
            String[] line=bufferedReader.readLine().split(" ");
            for (String temp : line) {
                arrayList.add(temp);
            }
        }
        bufferedReader.close();
        for (int i = 0; i < arrayList.size()-1; i++) {
            String fist=arrayList.get(i);
            if (fist==null)continue;
            String second=String.valueOf(new StringBuilder(fist).reverse());
            for (int j = i+1; j < arrayList.size(); j++) {
                if (arrayList.get(j)==null)continue;
                if (arrayList.get(j).equals(second)){
                    Pair pair=new Pair();
                    pair.first=fist;
                    pair.second=second;
                    result.add(pair);
                    arrayList.set(i,null);
                    arrayList.set(j,null);
                }
            }
        }
        System.out.println(result);
    }
    public static class Pair {
        String first;
        String second;
        @Override
        public String toString() {
            return first == null && second == null ? "" : first == null && second != null ? second : second == null && first != null ? first : first.compareTo(second) < 0 ? first + " " + second : second + " " + first;
        }
    }
}
