package com.javarush.test.level33.lesson15.big01.strategies;
import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Created by Игорь on 05.05.2016.
 */
public class FileBucket {
    private Path path;
    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }
    public long getFileSize() {
        try {
            return Files.size(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return 0;
    }
    public void putEntry(Entry entry) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(entry);
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        Entry entry = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path.toString()))) {
            entry = (Entry) inputStream.readObject();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }
    public void remove() {
        try {
            Files.deleteIfExists(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
