package com.javarush.test.level30.lesson04.home01;
import java.util.concurrent.TransferQueue;
/**
 * Created by golit on 17.03.2016.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;
    public Consumer(TransferQueue<ShareItem> queue) {this.queue = queue;}
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Processing "+ queue.take().toString());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
