package com.javarush.test.level29.lesson15.big01.user;
public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);
    public void printUsers() {
        userAnya.printInfo();
        userRoma.printInfo();
    }

    public int calculateAvarageAge() {
        int age = 28;
        User userUra = new User("Юра", "Карп", age);
        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;
    }
    public double calculateRate(double base, int age, boolean hasWork, boolean hasHouse) {
        double rezult=base;
        rezult += age / 100;
        rezult *= hasWork ? 1.1 : 0.9;
        rezult *= hasHouse ? 1.1 : 0.9;
        return rezult;
    }
    public String getBoosName(User user) {
        return user.getBoss();
    }
}
