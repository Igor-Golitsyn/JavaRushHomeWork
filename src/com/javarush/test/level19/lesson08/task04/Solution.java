package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream originalSOUT=System.out;
        ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
        PrintStream newStream=new PrintStream(byteArray);
        System.setOut(newStream);
        testString.printSomething();
        System.setOut(originalSOUT);
        String line=byteArray.toString();
        String[] result=line.split(" ");
        int num1=Integer.parseInt(result[0]);
        int num2=Integer.parseInt(result[2]);
        int itog=0;
        if (result[1].equals("+")) itog=num1+num2;
        if (result[1].equals("-")) itog=num1-num2;
        if (result[1].equals("*")) itog=num1*num2;
        System.out.printf(result[0]+" "+result[1]+" "+result[2]+" "+result[3]+" "+itog);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

