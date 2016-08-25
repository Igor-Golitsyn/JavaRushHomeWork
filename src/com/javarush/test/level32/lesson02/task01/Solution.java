package com.javarush.test.level32.lesson02.task01;
import java.io.RandomAccessFile;
/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            long seekInput = Long.parseLong(args[1]);
            long size = randomAccessFile.length();
            long seek = Long.compare(seekInput, size) > 0 ? size : seekInput;
            randomAccessFile.seek(seek);
            randomAccessFile.writeBytes(args[2]);
            randomAccessFile.close();
        }
        catch (Exception e) {
        }
    }
}
