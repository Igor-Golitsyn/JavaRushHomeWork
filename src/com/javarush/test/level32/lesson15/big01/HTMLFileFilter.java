package com.javarush.test.level32.lesson15.big01;
import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 * Created by golit on 18.04.2016.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        boolean rezult;
        String name=file.getName().toLowerCase();
        if (file.isDirectory()) rezult = true;
        else rezult = name.endsWith(".htm") || name.endsWith(".html");
        return rezult;
    }
    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
