package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }
    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws Exception {
        Set<Animal> animals = new HashSet<>();
        File[] classes = new File(pathToAnimals).listFiles();
        ClassLoader loader=new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    byte[] bytes= Files.readAllBytes(Paths.get(name));
                    return defineClass(null,bytes,0,bytes.length);
                }
                catch (IOException e) {
                }
                return super.findClass(name);
            }
        };
        for (int i = 0; i < classes.length; i++) {
            try {
                Class<?> clazz=loader.loadClass(classes[i].getAbsolutePath());
                animals.add((Animal) clazz.newInstance());
            }
            catch (Exception e) {
            }
        }
        return animals;
    }
}
