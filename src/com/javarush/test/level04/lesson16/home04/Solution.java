package com.javarush.test.level04.lesson16.home04;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int[] d=new int[3];
        String name=reader.readLine();
        for (int i = 0; i < 3; i++) {
            d[i]=Integer.parseInt(reader.readLine());
        }
        System.out.println("Меня зовут "+name);
        System.out.println("Я родился "+d[2]+"."+d[1]+"."+d[0]);

    }
}
