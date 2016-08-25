package com.javarush.test.level27.lesson15.big01;
import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Игорь on 25.12.2015.
 */
public class Tablet {
    private final static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;
    private LinkedBlockingQueue<Order> queue;
    public Tablet(int number) {
        this.number = number;
    }
    public void createOrder() {
        try {
            Order order = new Order(this);
            forCreateOrder(order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
    public void createTestOrder(){
        try {
            Order order = new TestOrder(this);
            forCreateOrder(order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
    private void forCreateOrder(Order order) {
        if (order.isEmpty()) return;
        ConsoleHelper.writeMessage(order.toString());
        queue.add(order);
        try {
            new AdvertisementManager((order.getTotalCookingTime()) * 60).processVideos();
        }
        catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }
    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }
}
