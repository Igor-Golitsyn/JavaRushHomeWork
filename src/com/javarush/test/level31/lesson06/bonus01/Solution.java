package com.javarush.test.level31.lesson06.bonus01;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> incomingFiles = new ArrayList<>(Arrays.asList(args));
        FileOutputStream fileOutputStream = new FileOutputStream(incomingFiles.get(0));
        incomingFiles.remove(0);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        Collections.sort(incomingFiles);
        for (String string : incomingFiles) {
            System.out.println(string);
            Files.copy(Paths.get(string), arrayOutputStream);
        }
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(arrayOutputStream.toByteArray()));
        int len;
        byte[] bytes = new byte[1024];
        zipInputStream.getNextEntry();
        while ((len=zipInputStream.read(bytes))>0){
            fileOutputStream.write(bytes,0,len);
        }
        zipInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}