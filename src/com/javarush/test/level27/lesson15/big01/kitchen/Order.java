package com.javarush.test.level27.lesson15.big01.kitchen;
import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;
/**
 * Created by Игорь on 25.12.2015.
 */
public class Order {
    private Cook cook;
    private Tablet tablet;
    protected List<Dish> dishes;
    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = initDishes();
    }
    @Override
    public String toString() {
        if (dishes.isEmpty()) return new String();
        return String.format("Your order: %s of %s", dishes.toString(), tablet);
    }
    public int getTotalCookingTime() {
        int cookingTime = 0;
        for (Dish dish : dishes) {
            cookingTime += dish.getDuration();
        }
        return cookingTime;
    }
    public boolean isEmpty() {
        return dishes.isEmpty();
    }
    public List<Dish> getDishes() {
        return dishes;
    }
    public Tablet getTablet() {
        return tablet;
    }
    protected List<Dish> initDishes() throws IOException {
        return ConsoleHelper.getAllDishesForOrder();
    }
    public Cook getCook() {
        return cook;
    }
    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
