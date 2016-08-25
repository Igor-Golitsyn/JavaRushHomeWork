package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>Touranga
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Solution {
    static String line;
    public static void main(String[] args) {
        try {
            String tag = args[0];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file = reader.readLine();
            reader.close();
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            line=new String();
            while (fileReader.ready()) {
                int temp=fileReader.read();
                if (temp==10 || temp==13){
                    temp=32;
                }
                line+=(char)temp;
            }
            fileReader.close();
            razborStroki(line,tag);
        }
        catch (Exception e) {
        }
    }
    static void razborStroki(String line, String tag) {
        String[] lineSplit = line.split("<");
        int max = 0;
        int tagN = 0;
        int start = 0;
        int end = 0;
        boolean found = false;
        for (int i = 0; i < lineSplit.length; i++) {
            if (lineSplit[i].startsWith(tag)) {
                tagN++;
                if (max < tagN) {max = tagN;}
                if (found == false) {
                    start = i;
                    found = true;
                }
            }
            if (lineSplit[i].startsWith("/" + tag)) {
                tagN--;
                end = i;
            }
            if (tagN == 0 && found) {
                line = new String();
                lineSplit[end] = "/"+tag+">";
                for (int j = start; j <= end; j++) {
                    System.out.print("<" + lineSplit[j]);
                    found = false;
                }
                System.out.println();
                if (max > 1) {
                    for (int j = start + 1; j < end; j++) {
                        line += "<" + lineSplit[j];
                    }
                    razborStroki(line, tag);
                }
            }
        }
    }
}
