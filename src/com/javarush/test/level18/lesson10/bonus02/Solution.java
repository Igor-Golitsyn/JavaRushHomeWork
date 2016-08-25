package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id

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
public class Solution {
    public static void main(String[] args) throws Exception {
        if (!args[0].equals("-c"))return;
        String line = null;
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String file=reader.readLine();
        reader.close();
        char[] charsID=new char[8];
        String id=new String();
        BufferedReader fileReader=new BufferedReader(new FileReader(file));
        while (fileReader.ready()){
            line=fileReader.readLine();
        }
        fileReader.close();
        line.getChars(0, 8, charsID, 0);
        for (char temp:charsID){
            if (temp==' ')continue;
            id+=temp;
        }
        int newId=Integer.parseInt(id);
        newId++;
        FileWriter fileWriter=new FileWriter(file,true);
        fileWriter.write("\n"+quantit(newId+"",8)+quantit(args[1],30)+quantit(args[2],8)+quantit(args[3],4));
        fileWriter.close();
    }
    public static String  quantit(String data, int lenght){
        while (data.length()<lenght){
            data+=" ";
        }
        return data;
    }
}
