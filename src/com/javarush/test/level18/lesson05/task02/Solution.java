package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки ввода-вывода

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        int schet = 0;
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        for (byte temp:bytes){
            if (temp==44)schet++;
        }
        System.out.println(schet);
    }
}
