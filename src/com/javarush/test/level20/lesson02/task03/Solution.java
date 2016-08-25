package com.javarush.test.level20.lesson02.task03;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file = reader.readLine();
            reader.close();
            BufferedReader fr = new BufferedReader(new FileReader(file));
            Properties prop = new Properties();
            prop.load(fr);
            for (String temp : prop.stringPropertyNames()) {
                properties.put(temp, prop.getProperty(temp));
            }
        }
        catch (IOException e) {
        }
    }
    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop=new Properties();
        prop.putAll(properties);
        prop.store(outputStream,this.toString());
    }
    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop=new Properties();
        prop.load(inputStream);
        for (String temp : prop.stringPropertyNames()) {
            properties.put(temp, prop.getProperty(temp));
        }
    }
}
