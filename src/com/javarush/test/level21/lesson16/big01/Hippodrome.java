package com.javarush.test.level21.lesson16.big01;
import java.util.ArrayList;
/**
 * Created by Игорь on 21.09.2015.
 */
public class Hippodrome {
    static ArrayList<Horse> horses = new ArrayList<>();
    public ArrayList<Horse> getHorses() {
        return horses;
    }
    public static Hippodrome game;
    public static void main(String[] args) {
        Hippodrome hippodrome = new Hippodrome();
        game = hippodrome;
        Horse horse1 = new Horse("HORSE1", 3, 0);
        Horse horse2 = new Horse("HORSE2", 3, 0);
        Horse horse3 = new Horse("HORSE3", 3, 0);
        game.horses.add(horse1);
        game.horses.add(horse2);
        game.horses.add(horse3);
        game.run();
        game.printWinner();
    }
    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
            }
        }
    }
    public void move() {
        for (Horse temp : horses) {
            temp.move();
        }
    }
    public void print() {
        for (Horse temp : horses) {
            temp.print();
        }
        System.out.println();
        System.out.println();
    }
    public Horse getWinner() {
        Horse winner = new Horse("winner", 0, 0);
        for (Horse h : getHorses()) {
            if (h.getDistance() > winner.getDistance()) {
                winner = h;
            }
        }
        return winner;
    }
    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
