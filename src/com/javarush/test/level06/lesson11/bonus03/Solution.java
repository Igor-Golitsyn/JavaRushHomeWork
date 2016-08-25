package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        int[] d = new int[5];
        for (int i = 0; i < 5; i++) {
            d[i] = Integer.parseInt(reader.readLine());
        }
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length - i - 1; j++) {
                if (d[j] > d[j + 1]) {
                    int s = d[j + 1];
                    d[j + 1] = d[j];
                    d[j] = s;
                }
            }
        }
        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i]);
        }
    }
}
