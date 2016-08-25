package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть поток

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String file=reader.readLine();
        reader.close();
        BufferedReader fr=new BufferedReader(new FileReader(file));
        while (fr.ready()){
            String line=fr.readLine();
            for (int i=line.length()-1; i>=0;i--){
                System.out.print(line.charAt(i));
            }
            System.out.println();
        }
    }
}
