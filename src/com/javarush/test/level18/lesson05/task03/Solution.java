package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки ввода-вывода
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file3 = reader.readLine();
            String file2 = reader.readLine();
            reader.close();
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            FileOutputStream fileOutputStream3 = new FileOutputStream(file3);
            byte[] bytes = new byte[fileInputStream1.available()];
            int common = fileInputStream1.read(bytes);
            fileInputStream1.close();
            fileOutputStream2.write(bytes, 0, common / 2);
            fileOutputStream3.write(bytes, common / 2, common - common / 2);
            fileOutputStream2.close();
            fileOutputStream3.close();
        } catch (Exception e) {
        }
    }
}
