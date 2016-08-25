package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //Написать тут ваш код
        Human baby1=new Human("baby1", true, 5);
        Human baby2=new Human("baby2", true, 7);
        Human baby3=new Human("baby3", false, 9);
        System.out.println(baby1);
        System.out.println(baby2);
        System.out.println(baby3);
        Human mother=new Human("mother", false, 30);
        mother.children.add(baby1);
        mother.children.add(baby2);
        mother.children.add(baby3);
        System.out.println(mother);
        Human father=new Human("father", true, 35);
        father.children.add(baby1);
        father.children.add(baby2);
        father.children.add(baby3);
        System.out.println(father);
        Human grMotherMother=new Human("GrMotherMother", false, 80);
        grMotherMother.children.add(mother);
        System.out.println(grMotherMother);
        Human grFatherMother=new Human("GrFatherMother", true, 85);
        grFatherMother.children.add(mother);
        System.out.println(grFatherMother);
        Human grMotherFather=new Human("GrMotherFather", false, 70);
        grMotherFather.children.add(father);
        System.out.println(grMotherFather);
        Human grFatherFather=new Human("GrFatherFather", true, 75);
        grFatherFather.children.add(father);
        System.out.println(grFatherFather);
    }

    public static class Human
    {
        //Написать тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children=new ArrayList<>();
        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;}

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
