package com.javarush.test.level06.lesson11.home01;

/* Класс Cat и статическая переменная catCount
В классе Cat создай статическую переменную public int catCount.
Создай конструктор [public Cat()]. Пусть при каждом создании кота (нового объекта Cat) статическая переменная
catCount увеличивается на 1. Создать 10 объектов Cat и вывести значение переменной catCount на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {   Cat[] d=new Cat[10];
        //Cоздай 10 объектов Cat тут
        for (int i = 0; i < 10; i++) {
            d[i]=new Cat();
        }

        // Выведи на экран catCount тут
        System.out.print(Cat.catCount);
    }

    public static class Cat
    {
        //Cоздай статическую переменную тут
        static int catCount;

        //создай конструктор тут
        Cat(){
            catCount++;
        }
    }

}
