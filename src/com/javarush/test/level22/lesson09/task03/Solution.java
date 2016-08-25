package com.javarush.test.level22.lesson09.task03;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) {
        //...
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file = reader.readLine();
            reader.close();
            FileReader fileReader = new FileReader(file);
            StringBuilder temp = new StringBuilder();
            while (fileReader.ready()) {
                temp.append((char) fileReader.read());
            }
            fileReader.close();
            String[] str = temp.toString().split(" ");
            StringBuilder result = getLine(str);
            System.out.println(result.toString());
        }
        catch (IOException e) {
        }
    }
    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();
        ArrayList<String> arrayList = new ArrayList<>();
        for (String temp : words) {
            if (temp == null) continue;
            if (temp.isEmpty()) continue;
            if (temp.equals(" ")) continue;
            arrayList.add(temp);
        }
        boolean itsNotOk;
        do {
            itsNotOk = false;
            Collections.shuffle(arrayList);
            for (int i = 0; i < arrayList.size() - 1; i++) {
                String first = arrayList.get(i);
                String subFirst = first.substring(first.length() - 1);
                String second = arrayList.get(i + 1);
                if (!(second.startsWith(subFirst.toLowerCase()) || second.startsWith(subFirst.toUpperCase()))) {
                    itsNotOk = true;
                    break;
                }
            }
        }
        while (itsNotOk);
        StringBuilder outLine = new StringBuilder();
        for (String temp : arrayList) {
            outLine.append(temp + " ");
        }
        outLine.deleteCharAt(outLine.length() - 1);
        return outLine;
    }
}
