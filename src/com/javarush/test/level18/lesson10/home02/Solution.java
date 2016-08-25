package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/
import java.io.FileInputStream;
import java.io.IOException;
public class Solution {
    public static void main(String[] args) {
        try {
            int breaks=0;
            int allsymb=0;
            FileInputStream fileInputStream=new FileInputStream(args[0]);
            allsymb=fileInputStream.available();
            while (fileInputStream.available()>0){
                if (fileInputStream.read()==32)breaks++;
            }
            double result=(double)breaks/allsymb*10000;
            result=(double)Math.round(result)/100;
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
