package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        String line;
        char[] charLine = new char[8];
        int idInt;
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        ArrayList<String> dataFromFile = new ArrayList<>();
        while (fileReader.ready()) {
            String idString = new String();
            line = fileReader.readLine();
            line.getChars(0, 8, charLine, 0);
            for (char temp : charLine) {
                if (temp == ' ') continue;
                idString += temp;
            }
            idInt = Integer.parseInt(idString);
            if (idInt == Integer.parseInt(args[1])) {
                if (args[0].equals("-d")) continue;
                if (args[0].equals("-u")) {
                    line = quantit(args[1], 8) + quantit(args[2], 30) + quantit(args[3], 8) + quantit(args[4], 4);
                }
            }
            dataFromFile.add(line);
        }
        fileReader.close();
        FileWriter fileWriter = new FileWriter(file);
        for (String temp : dataFromFile) {
            fileWriter.write(temp+"\n");
        }
        fileWriter.close();
    }
    public static String quantit(String data, int lenght) {
        while (data.length() < lenght) {
            data += " ";
        }
        return data;
    }
}
