package com.javarush.test.level25.lesson05.home01;
/**
 * Created by Игорь on 23.11.2015.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;
    private Thread.State state;
    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        setDaemon(true);
    }
    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != thread.getState()) {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}
