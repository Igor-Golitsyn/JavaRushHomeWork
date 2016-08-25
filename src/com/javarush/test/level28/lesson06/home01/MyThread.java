package com.javarush.test.level28.lesson06.home01;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by Игорь on 02.02.2016.
 */
public class MyThread extends Thread {
    private static AtomicInteger priority = new AtomicInteger(Thread.MIN_PRIORITY);
    public MyThread() {
        super();
        setPriority();
    }
    public MyThread(Runnable target) {
        super(target);
        setPriority();
    }
    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority();
    }
    public MyThread(String name) {
        super(name);
        setPriority();
    }
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority();
    }
    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority();
    }
    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority();
    }
    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority();
    }
    private void setPriority() {
        setPriority(priority.getAndIncrement());
        priority.compareAndSet(Thread.MAX_PRIORITY+1,Thread.MIN_PRIORITY);
    }
}
