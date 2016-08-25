package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human {
        boolean male;
        int age;
        boolean skin;
        String fistName;
        String secondName;
        String family;

        //напишите тут ваши переменные и конструкторы
        Human(boolean male, int age, boolean skin, String fistName, String secondName, String family) {
            this.male = male;
            this.age = age;
            this.skin = skin;
            this.fistName = fistName;
            this.secondName = secondName;
            this.family = family;
        }
        Human(String fistName, String secondName, String family) {
            this.fistName=fistName;
            this.secondName=secondName;
            this.family=family;
        }
        Human() {
        }
        Human(boolean male){
            this.male=male;
        }
        Human(int age){
            this.age=age;
        }
        Human(String family){
            this.family=family;
        }
        Human(String family, boolean skin){
            this.family=family;
            this.skin=skin;
        }
        Human(boolean male, String family){
            this.male=male;
            this.family=family;
        }
        Human(boolean male,boolean skin){
            this.male=male;
            this.skin=skin;
        }
        Human(int age, String family){
            this.age=age;
            this.family=family;
        }
    }
}
