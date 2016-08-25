package com.javarush.test.level34.lesson15.big01.model;
import java.awt.*;
/**
 * Created by Игорь on 23.05.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics graphics) {
        int centerX = getX() - getWidth() / 2;
        int centerY = getY() - getHeight() / 2;
        graphics.setColor(new Color(165,42,42));
        graphics.drawRect(centerX,centerY,getWidth(),getHeight());
        graphics.fillRect(centerX,centerY,getWidth(),getHeight());
    }
}
