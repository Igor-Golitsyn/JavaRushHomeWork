package com.javarush.test.level14.lesson08.bonus01;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/
public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();
    public static void main(String[] args) {
        initExceptions();
        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }
    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            int[] mas = new int[10];
            int i = mas[11];
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            new FileInputStream("c:/er.txt");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            int i=Integer.parseInt("ser");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            new FileOutputStream("c:");
        }catch (Exception e){
            exceptions.add(e);
        }
        try {
            throw new InterruptedException();
        }catch (Exception e){
            exceptions.add(e);
        }
        try {
            throw new NamingException();
        }catch (Exception e){
            exceptions.add(e);
        }
        try {
            throw new UnsupportedOperationException();
        }catch (Exception e){
            exceptions.add(e);
        }
        try {
            throw new RuntimeException();
        }catch (Exception e){
            exceptions.add(e);
        }
        try {
            throw new NamingException("dsfrd");
        }catch (Exception e){
            exceptions.add(e);
        }
    }
}
