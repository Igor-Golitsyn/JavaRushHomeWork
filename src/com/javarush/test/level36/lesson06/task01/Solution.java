package com.javarush.test.level36.lesson06.task01;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }
    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        ArrayList<Class> classArrayList = new ArrayList<>();
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        for (Class clazz : classes) {
            if (List.class.isAssignableFrom(clazz) && clazz.getModifiers() == 10) {
                classArrayList.add(clazz);
                Method[] methods = clazz.getDeclaredMethods();
                //System.out.println(methods.length + "\t metods in " + clazz.getSimpleName() + " " + Modifier.toString(clazz.getModifiers()));
                try {
                    Method method = clazz.getMethod("get", int.class);
                    //method.invoke(list,1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return classArrayList.get(1);
    }
}
