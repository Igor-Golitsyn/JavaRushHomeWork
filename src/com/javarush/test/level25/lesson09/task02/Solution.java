package com.javarush.test.level25.lesson09.task02;

import java.util.Timer;
import java.util.TimerTask;

/* Вооружаемся до зубов!
Создайте свой UncaughtExceptionHandler в виде локального класса внутри конструктора.
UncaughtExceptionHandler должен маскать звездочками имя трэда.
"Thread-0" должно быть заменено на "********"
"Thread-4321" должно быть заменено на "***********"
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        class LocalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String name=t.getName();
                String star=new String();
                for (int i = 0; i < name.length(); i++) {
                    star+="*";
                }
                System.out.println(e.getMessage().replaceAll(t.getName(),star));
            }
        }
        this.handler=new LocalUncaughtExceptionHandler();
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }
    public static void main(String[] args) {
        Solution solution=new Solution(new TimerTask() {
            @Override
            public void run() {
                System.out.println("GO!");
                int x=3/0;
            }
        });
        Timer timer=new Timer();
        timer.schedule(solution,1);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        timer.cancel();
    }
}