package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/
import java.io.IOException;
import java.nio.file.Files;
public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        //напишите тут ваш код
        int b = (int) new Object();
    }
    public void methodThrowsNullPointerException() {
        //напишите тут ваш код
        try {
            Files.createTempFile("1", "2", null);
        }
        catch (IOException e) {
        }
    }
}
