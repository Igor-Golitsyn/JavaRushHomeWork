package com.javarush.test.level27.lesson09.task01;
import java.util.concurrent.CountDownLatch;
/**
 * Created by Игорь on 24.12.2015.
 */
public class HungryStudent implements Runnable {
    static CountDownLatch c;
    public void run() {
        try {
            Thread.sleep(10);
            c.await();
            System.out.println("Cтудент поел");
        }
        catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        int n = 5;
        new Thread(new HungryStudent()).start();
        c = new CountDownLatch(n);
        for (int i = 0; i < n; i++)
            new Thread(new Kock()).start();
    }
}
class Kock implements Runnable {
    static public int count = 0;
    private int id = count++;
    public void run() {
        System.out.println("Готова еда от Кок№" + id);
        HungryStudent.c.countDown();
    }
}
