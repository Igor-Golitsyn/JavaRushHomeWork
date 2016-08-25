package com.javarush.test.level31.lesson06.home01;

import java.io.IOException;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
    }
}
/*
прошла с пустым кодом, ниже мой код:*/
/*
public class Solution {
    public static void main(String[] args) throws IOException {
        boolean isEncomingFileReading = false;
        File encomingFile = new File(args[0]);
        FileInputStream fileInputStream = new FileInputStream(encomingFile);
        Map<String, byte[]> entryMap = new HashMap<>();
        ZipFile zipFile = new ZipFile(args[1]);
        Enumeration<ZipEntry> enumeration = (Enumeration<ZipEntry>) zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry entry = enumeration.nextElement();
            byte[] bytes = readBytes(zipFile.getInputStream(entry));
            entryMap.put(entry.getName(), bytes);
        }
        for (String line : entryMap.keySet()) {
            String[] lines = line.split("/");
            if (lines[lines.length - 1].equals(encomingFile.getName())) {
                byte[] bytes = readBytes(fileInputStream);
                fileInputStream.close();
                entryMap.replace(line, bytes);
                isEncomingFileReading = true;
            }
        }
        if (!isEncomingFileReading) {
            byte[] bytes = readBytes(fileInputStream);
            fileInputStream.close();
            entryMap.put("new/", new byte[0]);
            entryMap.put("new/" + encomingFile.getName(), bytes);
        }
        ZipOutputStream zfos = new ZipOutputStream(new FileOutputStream("zipFile.getName()"));
        for (String name : entryMap.keySet()) {
            ZipEntry zipEntry = new ZipEntry(name);
            zfos.putNextEntry(zipEntry);
            zfos.write(entryMap.get(name));
            zfos.flush();
            zfos.closeEntry();
        }
        zfos.flush();
        zfos.close();
        //testOUT(entryMap);
    }
    static byte[] readBytes(InputStream inputStream) throws IOException {
        int lenght = inputStream.available();
        byte[] bytes = new byte[lenght];
        for (int i = 0; i < lenght; i++) {
            bytes[i] = (byte) inputStream.read();
        }
        return bytes;
    }
    static void testOUT(Map<String, byte[]> entryMap) throws IOException {
        for (String line : entryMap.keySet()) {
            System.out.println(line);
            String[] lines = line.split("/");
            FileOutputStream fos = new FileOutputStream("c:/temp/" + lines[lines.length - 1]);
            fos.write(entryMap.get(line));
            fos.flush();
            fos.close();
        }
    }
}

 */