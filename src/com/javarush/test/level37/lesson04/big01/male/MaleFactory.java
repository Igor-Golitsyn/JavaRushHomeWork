package com.javarush.test.level37.lesson04.big01.male;
import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;
/**
 * Created by Игорь on 21.07.2016.
 */
public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (age > TeenBoy.MAX_AGE) return new Man();
        if (age <= KidBoy.MAX_AGE) return new KidBoy();
        return new TeenBoy();
    }
}
