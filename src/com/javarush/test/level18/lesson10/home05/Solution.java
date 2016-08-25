package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            String file1=reader.readLine();
            String file2=reader.readLine();
            FileWriter fos2=new FileWriter(file2);
            BufferedReader fr1=new BufferedReader(new FileReader(file1));
            String[] line= fr1.readLine().split(" ");
            fr1.close();
            for (String temp:line){
                fos2.write(Math.round(Double.parseDouble(temp))+" ");
            }
            fos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
