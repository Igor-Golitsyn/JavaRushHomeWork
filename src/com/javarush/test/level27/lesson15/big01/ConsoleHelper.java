package com.javarush.test.level27.lesson15.big01;
import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Игорь on 25.12.2015.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static String readString() throws IOException {
        String line;
        line = reader.readLine();
        return line;
    }
    public static List<Dish> getAllDishesForOrder() throws IOException {
        String line;
        writeMessage("EXIT or select dishes: " + Dish.allDishesToString());
        List<String> dishList = new ArrayList<>();
        while (!("exit".equalsIgnoreCase(line = readString()))) {
            dishList.add(line);
        }
        List<Dish> resultDishList=new ArrayList<>();
        for (String dishes:dishList){
            if (Dish.allDishesToString().contains(dishes))resultDishList.add(Dish.valueOf(dishes));
            else writeMessage(dishes+" is not detected");
        }
        return resultDishList;
    }
}
