package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        //Написать тут ваш код
        ArrayList<int[]> list = new ArrayList<>();
        int[] l1 = new int[]{1, 2, 3, 4, 5};
        int[] l2 = new int[]{2, 1};
        int[] l3 = new int[]{5, 5, 5, 5};
        int[] l4 = new int[]{7, 7, 7, 7, 7, 7, 7};
        int[] l5 = new int[]{};
        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(l4);
        list.add(l5);
        return list;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
