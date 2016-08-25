package com.javarush.test.level13.lesson11.home03;
import java.io.*;

/* Запись в файл
 1. Прочесть с консоли имя файла.
 2. Считывать строки с консоли, пока пользователь не введет строку "exit".
 3. Вывести все строки в файл, каждую строчку с новой стороки.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String name=reader.readLine();
        FileWriter fileWriter=new FileWriter(name);
        while (true) {
            String line=reader.readLine()+"\n";
            if (line.matches("exit\n")) {
                fileWriter.close();
                reader.close();
                break;
            }
            fileWriter.write(line);
        }
    }
}
