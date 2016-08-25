package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки
*/
import java.io.FileInputStream;
import java.io.IOException;
public class Solution {
    public static void main(String[] args) {
        int bukvy=0;
        try {
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            while (fileInputStream.available() > 0) {
                int temp=fileInputStream.read();
                if (temp<91&temp>64||temp<123&temp>96) bukvy++;
            }
            fileInputStream.close();
            System.out.println(bukvy);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
