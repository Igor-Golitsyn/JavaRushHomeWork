package com.javarush.test.level27.lesson15.big01.kitchen;
import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Created by Игорь on 11.01.2016.
 */
public class Waitor implements Runnable {
    private LinkedBlockingQueue<Order> queueFinished;
    public void setQueueFinished(LinkedBlockingQueue<Order> queueFinished) {
        this.queueFinished = queueFinished;
    }
    @Override
    public void run() {
        int time = 0;
        boolean startWork = false;
        while (true) {
            synchronized (queueFinished) {
                if (queueFinished.size() > 0) {
                    Order order = queueFinished.poll();
                    ConsoleHelper.writeMessage(order + " was cooked by " + order.getCook().toString());
                    time = 0;
                    startWork = true;
                } else {
                    time++;
                    if (time > 100 && startWork) {
                        break;
                    }
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
