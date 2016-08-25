package com.javarush.test.level04.lesson16.home03;

/* Посчитать сумму чисел
Вводить с клавиатуры числа и считать их сумму. Если пользователь ввел -1, вывести на экран сумму и завершить программу.  -1 должно учитываться в сумме.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int summ=0;
        for (int i=0; !(i == -1) ; ) {
            i=Integer.parseInt(reader.readLine());
            summ+=i;
        }
        System.out.print(summ);
    }
}
