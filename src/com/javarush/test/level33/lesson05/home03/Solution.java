package com.javarush.test.level33.lesson05.home03;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
/* Десериализация JSON объекта
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть его.
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), clazz);
    }
    //********************************************test*********************************************************
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = File.createTempFile("000", null);
        mapper.writeValue(file, new Cat("vaska", 3));
        System.out.println(file.getAbsolutePath());
        Cat catttt = convertFromJsonToNormal(file.getAbsolutePath(), Cat.class);
        System.out.println(catttt);
        file.delete();
    }
    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public Cat() {
        }
        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
