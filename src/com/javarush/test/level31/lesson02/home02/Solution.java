package com.javarush.test.level31.lesson02.home02;
import java.io.File;
import java.io.IOException;
import java.util.*;
/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> fileQueue=new ArrayDeque<>();
        List<String> rezult=new ArrayList<>();
        fileQueue.addAll(Arrays.asList(new File(root).listFiles()));
        while (!fileQueue.isEmpty()){
            File temp=fileQueue.poll();
            if (temp.isDirectory()){
                fileQueue.addAll(Arrays.asList(temp.listFiles()));
            } else {
                rezult.add(temp.getAbsolutePath());
            }
        }
        return rezult;
    }
}
