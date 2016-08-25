package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        try {
            String file=reader.readLine();
            reader.close();
            BufferedReader fr=new BufferedReader(new FileReader(file));
            int slova=0;
            while (fr.ready()){
                slova+=fr.readLine().split("world").length-1;
            }
            fr.close();
            System.out.println(slova);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
