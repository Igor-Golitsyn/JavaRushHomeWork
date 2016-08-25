package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные на сегодняшний день данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/
public class Solution implements Serializable {
    public Solution() {
    }
    public static void main(String[] args) {
        System.out.println(new Solution(4));
        try {
            File file= File.createTempFile("TempFile",null);
            ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(file));
            Solution savedObject=new Solution(5);
            outputStream.writeObject(savedObject);
            outputStream.close();
            Solution loadedObject=new Solution(9);
            System.out.println(savedObject.toString());
            System.out.println(loadedObject.toString());
            loadedObject=(Solution)inputStream.readObject();
            inputStream.close();
            System.out.println(savedObject.toString());
            System.out.println(loadedObject.toString());
            System.out.println(loadedObject.pattern);
            System.out.println(loadedObject.temperature);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
