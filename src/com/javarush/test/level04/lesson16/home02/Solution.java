package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int[] d=new int[3];
        for (int i = 0; i < 3; i++) {
            d[i]=Integer.parseInt(reader.readLine());
        }
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length - i - 1; j++) {
                if (d[j] < d[j + 1]) {
                    int s = d[j + 1];
                    d[j + 1] = d[j];
                    d[j] = s;
                }
            }
        }
        System.out.println(d[1]);
    }
}
