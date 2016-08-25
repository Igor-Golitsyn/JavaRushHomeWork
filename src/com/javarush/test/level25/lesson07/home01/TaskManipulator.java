package com.javarush.test.level25.lesson07.home01;
public class TaskManipulator implements Runnable, CustomThreadManipulator {
    volatile Thread thread;
    volatile String threadName;
    @Override
    public void run() {
        //System.out.println("----------RUN " + threadName);
        try {
            while (!thread.isInterrupted()) {
                //thread.sleep(0);
                System.out.println(threadName);
                thread.sleep(90);
            }
        }
        catch (InterruptedException e) {
        }
    }
    @Override
    public void start(String threadName) {
        try {
            Thread.sleep(1);
        }
        catch (InterruptedException e) {
        }
        this.threadName = threadName;
        this.thread = new Thread(this);
        thread.start();
    }
    @Override
    public void stop() {
        thread.interrupt();
    }
}
