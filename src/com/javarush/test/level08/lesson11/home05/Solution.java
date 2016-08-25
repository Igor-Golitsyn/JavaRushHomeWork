package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        char[]chToken;
        for (StringTokenizer stringTokenizer = new StringTokenizer(s); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            chToken=token.toCharArray();
            chToken[0]=Character.toUpperCase(chToken[0]);
            String newToken=new String(chToken);
            s=s.replaceAll(token, newToken);
        }
        //Напишите тут ваш код
        System.out.println(s);
    }


}
