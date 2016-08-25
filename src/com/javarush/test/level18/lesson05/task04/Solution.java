package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            reader.close();
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            for (int i = bytes.length - 1; i >= 0; i--) {
                fileOutputStream.write(bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
