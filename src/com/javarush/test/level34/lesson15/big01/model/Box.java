package com.javarush.test.level34.lesson15.big01.model;
import java.awt.*;
/**
 * Created by Игорь on 19.05.2016.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics graphics) {
        int centerX = getX() - getWidth() / 2;
        int centerY = getY() - getHeight() / 2;
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(centerX, centerY, getWidth(), getHeight());
        graphics.drawRect(centerX, centerY, getWidth(), getHeight());
    }
    @Override
    public void move(int x, int y) {
        setX(getX()+x);
        setY(getY()+y);
    }
}
