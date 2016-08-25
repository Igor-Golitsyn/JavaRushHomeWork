package com.javarush.test.level27.lesson15.big01;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by Игорь on 26.01.2016.
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Tablet tempTablet = tablets.get(ThreadLocalRandom.current().nextInt(tablets.size()));
            tempTablet.createTestOrder();
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }
}
