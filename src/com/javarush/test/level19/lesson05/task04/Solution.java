package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки ввода-вывода.
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String file1,file2;
        file1=reader.readLine();
        file2=reader.readLine();
        reader.close();
        BufferedReader fileReader=new BufferedReader(new FileReader(file1));
        FileWriter fileWriter=new FileWriter(file2);
        while (fileReader.ready()){
            fileWriter.write(fileReader.readLine().replaceAll("\\.","!")+"\n");
        }
        fileReader.close();
        fileWriter.close();
    }
}
