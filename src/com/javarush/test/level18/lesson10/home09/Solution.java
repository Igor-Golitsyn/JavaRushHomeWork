package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение, вывести в консоль переданное неправильное имя файла и завершить работу программы
Не забудьте закрыть все потоки
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = null;
        FileInputStream fis=null;
        while (true) {
            try {
                file = reader.readLine();
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                try {
                    reader.close();
                    fis.close();
                } catch (IOException e1) {
                }
                System.out.println(file);
                break;
            } catch (IOException e) {
            }
        }
    }
}
