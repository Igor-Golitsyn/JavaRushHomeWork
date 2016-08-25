package com.javarush.test.level27.lesson15.big01.ad;
/**
 * Created by Игорь on 12.01.2016.
 */
public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long balance;
    private long amountPerOneDisplaying;
    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.balance=initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }
    public String getName() {
        return name;
    }
    public int getDuration() {
        return duration;
    }
    public long getAmountPerOneDisplaying() {
        return balance/hits;
    }
    public void revalidate() {
        amountPerOneDisplaying=balance/hits;
        balance-=amountPerOneDisplaying;
        if (hits <= 0) throw new UnsupportedOperationException();
        hits--;
    }
    public int getHits() {
        return hits;
    }
}
