package com.javarush.test.level37.lesson04.big01;
import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;
/**
 * Created by Игорь on 21.07.2016.
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(HumanFactoryType factoryType) {
        switch (factoryType) {
            case MALE:
                return new MaleFactory();
            case FEMALE:
                return new FemaleFactory();
        }
        return null;
    }
    public enum HumanFactoryType {
        MALE, FEMALE
    }
}
