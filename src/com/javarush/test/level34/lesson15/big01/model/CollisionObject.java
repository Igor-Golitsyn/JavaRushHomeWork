package com.javarush.test.level34.lesson15.big01.model;
/**
 * Created by Игорь on 19.05.2016.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }
    public boolean isCollision(GameObject gameObject, Direction direction) {
        int forwardX = getX();
        int forwardY = getY();
        switch (direction) {
            case LEFT:
                forwardX -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                forwardX += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                forwardY -= Model.FIELD_SELL_SIZE;
                break;
            case DOWN:
                forwardY += Model.FIELD_SELL_SIZE;
        }
        if (gameObject.getX() == forwardX && gameObject.getY() == forwardY) {
            return true;
        }
        return false;
    }
}
