package com.javarush.test.level20.lesson10.home02;
import java.io.*;
/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {
    private static final long serialVersionUID = 1L;
    public A getOriginalObject(ObjectInputStream objectStream) {
        Object object = new Object();
        try {
            object = objectStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (object instanceof B) { return (B) object;}
        return (A) object;
    }
    public class A implements Serializable {
        private static final long serialVersionUID = 1L;
    }
    public class B extends A {
        private static final long serialVersionUID = 1L;
        public B() {
            System.out.println("inside B");
        }
    }
}
