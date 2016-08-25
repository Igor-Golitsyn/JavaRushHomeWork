package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) throws Exception {
        String file1 = null, file2;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        file1 = reader.readLine();
        file2 = reader.readLine();
        reader.close();
        FileInputStream fis1 = new FileInputStream(file1);
        FileOutputStream fos2 = new FileOutputStream(file2);
        int[] temp=new int[fis1.available()];
        for (int i=0; i<temp.length;i++){
            temp[i]=fis1.read();
        }
        System.out.println();
        fis1.close();
        for (int i = 1; i < temp.length; i=i+2) {
            fos2.write(temp[i]);
        }
        fos2.close();
    }
}
