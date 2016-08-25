package com.javarush.test.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор. Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12 , 5);

        //Напишите тут ваш код
        Dog bigDog=new Dog("SPIKE",10,50);
        Dog smallDog=new Dog("small",1,10);
        Cat tomCat=new Cat("TOM",10,"home");
        Cat bomjCat=new Cat(null,10,null);
    }

    public static class Mouse
    {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }
    public static class Dog{
        String name;
        int age;
        int height;
        public Dog(String name,int age,int height){
            this.name=name;
            this.age=age;
            this.height=height;
        }
    }
    public static class Cat{
        String name;
        int age;
        String adr;
        public Cat(String name,int age,String adr){
            this.name=name;
            this.age=age;
            this.adr=adr;
        }
    }

    //Напишите тут ваши классы

}
