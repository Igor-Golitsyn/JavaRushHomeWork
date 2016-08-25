package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class Solution {
    public static TestString testString = new TestString();
    public static void main(String[] args) {
        PrintStream orignalSOUT = System.out;
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(byteArray);
        System.setOut(newStream);
        testString.printSomething();
        System.setOut(orignalSOUT);
        byte[] bytes = byteArray.toByteArray();
        int chet=0;
        for (int i = 0; i < bytes.length; i++) {
            System.out.print((char) bytes[i]);
            if (bytes[i]==10)chet++;
            if (bytes[i]==10&&chet%2==0) System.out.println("JavaRush - курсы Java онлайн");
        }
    }
    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
