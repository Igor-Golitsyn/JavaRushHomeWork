package com.javarush.test.level37.lesson04.big01.female;
import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;
/**
 * Created by Игорь on 21.07.2016.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (age > TeenGirl.MAX_AGE) return new Woman();
        if (age <= KidGirl.MAX_AGE) return new KidGirl();
        return new TeenGirl();
    }
}
