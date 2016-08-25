package com.javarush.test.level07.lesson12.home06;

/* Семья
 Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human).
 Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
 Примечание:
 Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
 Пример вывода:
 Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
 Имя: Катя, пол: женский, возраст: 55
 Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
 …
 */
public class Solution {

    public static void main(String[] args) {
        //Написать тут ваш код
        Human gFather1=new Human("gFather1",true,55,null,null);
        Human gFather2=new Human("gFather2", true, 56, null, null);
        Human gMather1=new Human("gMather1", Boolean.FALSE, 50, null, null);
        Human gMather2=new Human("gMather2", Boolean.FALSE, 51, null, null);
        Human father=new Human("father", Boolean.TRUE, 30, gFather1, gMather1);
        Human mather=new Human("mather", Boolean.FALSE, 29, gFather2, gMather2);
        Human child1=new Human("child1", Boolean.TRUE, 10, father, mather);
        Human child2=new Human("child2", Boolean.FALSE, 6, father, mather);
        Human child3=new Human("child3", Boolean.TRUE, 3, father, mather);
        System.out.println(gFather1);
        System.out.println(gFather2);
        System.out.println(gMather1);
        System.out.println(gMather2);
        System.out.println(father);
        System.out.println(mather);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human {
        String name;
        Boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String name, Boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        //Написать тут ваш код

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }

}
