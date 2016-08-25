package com.javarush.test.level30.lesson04.home01;
import java.util.concurrent.TransferQueue;
/**
 * Created by golit on 17.03.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;
    public Producer(TransferQueue<ShareItem> queue) {this.queue = queue;}
    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()){
            for (int i = 1; i < 10; i++) {
                System.out.format("Элемент 'ShareItem-%d' добавлен\n",i);
                queue.offer(new ShareItem("ShareItem-"+i, i));
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    break;
                }
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            }
        }
    }
}
