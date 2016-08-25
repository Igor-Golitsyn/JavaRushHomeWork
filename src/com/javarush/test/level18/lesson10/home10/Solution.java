package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = null;
        String resultFile = null;
        Map<String, String> filesMap = new TreeMap<>();
        while (true) {
            String  fileTemp = reader.readLine();
            if (fileTemp.equals("end")) break;
            File f = new File(fileTemp);
            String fileName = f.getName();
            filePath = f.getPath().replaceAll(fileName, "");
            String[] fileSplit = fileName.split("\\.");
            filesMap.put(fileSplit[fileSplit.length - 1], fileTemp);
            resultFile = fileName.replaceAll("\\."+fileSplit[fileSplit.length - 1], "");
        }
        reader.close();
        FileOutputStream fos = new FileOutputStream(filePath + resultFile);
        for (String temp:filesMap.keySet()){
            FileInputStream fis=new FileInputStream(filesMap.get(temp));
            while (fis.available()>0){
                fos.write(fis.read());
            }
            fis.close();
        }
        fos.close();
    }
}
