package com.javarush.test.level10.lesson11.home08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args) throws IOException {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() throws IOException {
        //Напишите тут ваш код
        ArrayList<String>[] arrayOfStringList = new ArrayList[3];
        ArrayList<String> stringList1=new ArrayList<>();
        stringList1.add("stringlist1");
        ArrayList<String> stirngList2=new ArrayList<>();
        stirngList2.add("stringlist2");
        ArrayList<String> stringList3=new ArrayList<>();
        stringList3.add("stringlist3");
        arrayOfStringList[0]=stringList1;
        arrayOfStringList[1]=stirngList2;
        arrayOfStringList[2]=stringList3;

        return arrayOfStringList;

    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}