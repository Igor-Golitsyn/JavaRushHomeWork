package com.javarush.test.level05.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum;
        int N=Integer.parseInt(reader.readLine());
        int[] d=new int[N];
        for (int i = 0; i < N; i++) {
            d[i]=Integer.parseInt(reader.readLine());
        }
        maximum=d[0];
        for (int i = 0; i < N-1; i++) {
            maximum=maximum>d[i+1]?maximum:d[i+1];
        }
        //напишите здесь ваш код




        System.out.println(maximum);
    }
}
