package com.javarush.test.level34.lesson15.big01.model;
import java.awt.*;
/**
 * Created by Игорь on 23.05.2016.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }
    @Override
    public void draw(Graphics graphics) {
        int centerX = getX() - getWidth() / 2;
        int centerY = getY() - getHeight() / 2;
        graphics.setColor(Color.RED);
        graphics.drawOval(centerX,centerY,getWidth(),getHeight());
        graphics.fillOval(centerX,centerY,getWidth(),getHeight());
    }
}
