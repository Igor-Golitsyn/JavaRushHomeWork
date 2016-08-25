package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) throws Exception {
        String file1 = args[0];
        String file2 = args[1];
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));
        while (fileReader.ready()) {
            String[] lineSplit =fileReader.readLine().split(" ");
            for (String temp : lineSplit) {
                String newTemp = temp.replaceAll("\\d", "");
                if (newTemp.length() < temp.length()) {
                    fileWriter.write(temp + " ");
                }
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
