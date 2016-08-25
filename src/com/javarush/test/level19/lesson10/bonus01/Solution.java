package com.javarush.test.level19.lesson10.bonus01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/
public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        BufferedReader fr1 = new BufferedReader(new FileReader(file1));
        BufferedReader fr2 = new BufferedReader(new FileReader(file2));
        ArrayList<String> f2Massiv = new ArrayList<>();
        while (fr2.ready()) {
            f2Massiv.add(fr2.readLine());
        }
        int num = 0;
        while (fr1.ready()) {
            boolean notFound = true;
            String line = fr1.readLine();
            for (int i = num; i < f2Massiv.size(); i++) {
                if (line.equals(f2Massiv.get(i))) {
                    for (int j = num; j < i; j++) {
                        lines.add(new LineItem(Type.ADDED, f2Massiv.get(j)));
                    }
                    lines.add(new LineItem(Type.SAME, line));
                    num = i + 1;
                    notFound = false;
                    break;
                }
            }
            if (notFound) lines.add(new LineItem(Type.REMOVED, line));
        }
        if (num < f2Massiv.size()) {
            for (int i = num; i < f2Massiv.size(); i++) {
                lines.add(new LineItem(Type.ADDED, f2Massiv.get(i)));
            }
        }
        for (LineItem temp : lines) {
            System.out.println(temp.type + "  " + temp.line);
        }
    }
    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }
    public static class LineItem {
        public Type type;
        public String line;
        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
