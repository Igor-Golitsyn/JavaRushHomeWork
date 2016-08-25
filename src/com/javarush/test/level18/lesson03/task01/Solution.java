package com.javarush.test.level18.lesson03.task01;
import java.io.BufferedReader;/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        int max = 0;
        while (fileInputStream.available() > 0) {
            int temp = fileInputStream.read();
            if (max < temp) max = temp;
        }
        fileInputStream.close();
        System.out.println(max);
    }
}
