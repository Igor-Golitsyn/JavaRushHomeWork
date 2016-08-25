package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class Solution {
    public static void main(String[] args) throws Exception {
        String file1 = args[0];
        String file2 = args[1];
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));
        String stringOut = new String();
        while (fileReader.ready()) {
            String[] splitLine = fileReader.readLine().split(" ");
            for (String temp : splitLine) {
                if (temp.length() > 6) stringOut = stringOut.concat(temp + ",");
            }
        }
        fileWriter.write(stringOut.substring(0, stringOut.length() - 1));
        fileReader.close();
        fileWriter.close();
    }
}
