package com.javarush.test.level27.lesson15.big01.kitchen;
import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Created by Игорь on 30.12.2015.
 */
public class Cook implements Runnable {
    private String name;
    private LinkedBlockingQueue<Order> queue;
    private LinkedBlockingQueue<Order> queueFinished;
    public Cook(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + (order.getTotalCookingTime() + "min"));
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        order.setCook(this);
        synchronized (queueFinished) {
            queueFinished.add(order);
        }
    }
    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }
    public void setQueueFinished(LinkedBlockingQueue<Order> queueFinished) {
        this.queueFinished = queueFinished;
    }
    @Override
    public void run() {
        int time = 0;
        while (true) {
            synchronized (queue) {
                if (queue.size() > 0) {
                    startCookingOrder(queue.poll());
                    time = 0;
                } else {
                    time++;
                    if (time > 100) break;
                }
            }
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
            }
        }
    }
}
