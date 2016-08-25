package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String file1=reader.readLine();
        String file2=reader.readLine();
        String file3=reader.readLine();
        FileOutputStream fos1=new FileOutputStream(file1);
        FileInputStream fis2=new FileInputStream(file2);
        FileInputStream fis3=new FileInputStream(file3);
        while (fis2.available()>0){
            fos1.write(fis2.read());
        }
        while (fis3.available()>0){
            fos1.write(fis3.read());
        }
        fos1.close();
        fis2.close();
        fis3.close();
    }
}
