package com.javarush.test.level13.lesson11.bonus01;
import java.io.*;
import java.util.ArrayList;
/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        InputStream data = new FileInputStream(fileName);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(data));
        ArrayList<Integer> intArray = new ArrayList<>();
        while (fileReader.ready()) {
            int temp = Integer.parseInt(fileReader.readLine());
            if (temp % 2 == 0) {
                intArray.add(temp);
            }
        }
        reader.close();
        fileReader.close();
        for (int i = 0; i < intArray.size(); i++) {
            for (int j = i + 1; j < intArray.size(); j++) {
                if (intArray.get(i) > intArray.get(j)) {
                    int temp = intArray.get(i);
                    intArray.set(i, intArray.get(j));
                    intArray.set(j, temp);
                }
            }
        }
        for (Integer intArray1 : intArray) {
            System.out.println(intArray1);
        }
    }
}
