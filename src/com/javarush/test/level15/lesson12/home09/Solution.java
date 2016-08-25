package com.javarush.test.level15.lesson12.home09;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк
Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name
Пример 2
Ввод:
a.ua?oobj=fff
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
javarush.ru/alpha/index.html?lvl=15&??view&&&name=Aobjmigo&obj=3.14&name=
javarush.ru/alpha/index.html?lvl=15&??view&&&name=Aobjmigo&obj===3.14&&&&name=&obj=3.14&&obj=314&&??==&&.&..&==&obg&&=&&===&oobj=&??&???&&?=?
Вывод:
obj name
double 3.14
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        char[] charsUrl = url.toCharArray();
        ArrayList<Integer> simbNumber = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();
        String text = new String();
        for (int i = 0; i < charsUrl.length; i++) {
            if (charsUrl[i] == '?' || charsUrl[i] == '&') simbNumber.add(i);
        }
        simbNumber.add(charsUrl.length);
        for (int i = 0; i < simbNumber.size() - 1; i++) {
            data.add(url.substring(simbNumber.get(i) + 1, simbNumber.get(i + 1)));
        }
        for (String temp : data) {
            if (temp.contains("=")) text += temp.substring(0, temp.indexOf("=")) + " ";
            else if (temp.isEmpty()) continue;
            else text += temp + " ";
        }

        System.out.println(text);
        for (String temp:data){
            if (temp.startsWith("obj=",0)){
                try {
                    double val=Double.parseDouble(temp.substring(temp.indexOf("=")+1));
                    alert(val);
                } catch (Exception e){
                    alert(temp.substring(temp.indexOf("=")+1));
                }
            }
        }
    }
    public static void alert(double value) {
        System.out.println("double " + value);
    }
    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
