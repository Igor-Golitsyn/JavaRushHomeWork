package com.javarush.test.level27.lesson15.big01;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Created by Игорь on 25.12.2015.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Order> queueFinished=new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<Order> getQueue() {
        return queue;
    }
    public static void main(String[] args) {
        Cook cook = new Cook("Amigo");
        Cook cook1 = new Cook("Famigo");
        Waitor waitor = new Waitor();
        cook.setQueue(queue);
        cook.setQueueFinished(queueFinished);
        cook1.setQueue(queue);
        cook1.setQueueFinished(queueFinished);
        waitor.setQueueFinished(queueFinished);
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet tempTablet = new Tablet(i);
            tempTablet.setQueue(queue);
            tablets.add(tempTablet);
        }
        Thread random = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        Thread coook = new Thread(cook);
        Thread coook1 = new Thread(cook1);
        Thread threadWaitor=new Thread(waitor);
        threadWaitor.start();
        coook.start();
        coook1.start();
        random.start();
        try {
            Thread.sleep(20000);
        }
        catch (InterruptedException e) {
        }
        random.interrupt();
        try {
            coook.join();
            coook1.join();
            threadWaitor.join();
        }
        catch (InterruptedException e) {
        }
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printNoVideAvailible();
    }
}
