package com.javarush.test.level36.lesson08.task01;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> characters = new TreeSet<>();
        byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
        for (byte byt : bytes) {
            if (Character.isLetter(byt)) characters.add(Character.toLowerCase((char) byt));
        }
        int limit = 5;
        for (Character ch : characters) {
            if (limit == 0) return;
            System.out.print(ch);
            limit--;
        }
    }
}
