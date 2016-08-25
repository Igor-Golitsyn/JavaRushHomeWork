package com.javarush.test.level08.lesson11.home01;

import java.util.HashSet;
import java.util.Set;

/* Set из котов
 1. Внутри класса Solution создать public static класс кот – Cat.
 2. Реализовать метод createCats, он должен создавать множество (Set) котов и добавлять в него 3 кота.
 3. В методе main удалите одного кота из Set cats.
 4. Реализовать метод printCats, он должен вывести на экран всех котов, которые остались во множестве.
 Каждый кот с новой строки.
 */
public class Solution {
    public static void main(String[] args) {
        Cat b=null;
        Set<Cat> cats = createCats();
        for (Cat cat : cats) {
            if (cat.name.equalsIgnoreCase("Петька")) {
                b=cat;
            }
        }
        cats.remove(b);
        //Написать тут ваш код. step 3 - пункт 3
        printCats(cats);
    }

    public static Set<Cat> createCats() {
        Set<Cat> tempcat = new HashSet<>();
        tempcat.add(new Cat("Васька"));
        tempcat.add(new Cat("Петька"));
        tempcat.add(new Cat("Мурка"));
        //Написать тут ваш код. step 2 - пункт 2
        return tempcat;
    }

    public static void printCats(Set<Cat> cats) {
        for (Cat cat : cats) {
            System.out.println(cat);
        }
        // step 4 - пункт 4
    }

    // step 1 - пункт 1
    public static class Cat {
        String name;
        public Cat(String name) {
            this.name = name;
        }

    }
}
