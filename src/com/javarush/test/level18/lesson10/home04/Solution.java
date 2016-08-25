package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            String file1=reader.readLine();
            String file2=reader.readLine();
            FileInputStream fis1=new FileInputStream(file1);
            FileInputStream fis2=new FileInputStream(file2);
            byte[] bytesFile1=new byte[fis1.available()];
            fis1.read(bytesFile1);
            fis1.close();
            FileOutputStream fos1=new FileOutputStream(file1);
            while (fis2.available()>0){
                fos1.write(fis2.read());
            }
            for (byte temp:bytesFile1){
                fos1.write(temp);
            }
            fos1.close();
            fis2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
