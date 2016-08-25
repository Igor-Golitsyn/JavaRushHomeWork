package com.javarush.test.level34.lesson15.big01.model;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;
/**
 * Created by Игорь on 19.05.2016.
 */
public class Model {
    public static final int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("./src/" + this.getClass().getPackage().getName().replace(".", "/").replace("model", "res/levels.txt")));
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public GameObjects getGameObjects() {
        if (gameObjects == null) restart();
        return gameObjects;
    }
    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }
    public void restart() {
        restartLevel(currentLevel);
    }
    public void startNextLevel() {
        currentLevel++;
        restart();
    }
    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction) {
            case UP:
                gameObjects.getPlayer().move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                gameObjects.getPlayer().move(0, FIELD_SELL_SIZE);
                break;
            case LEFT:
                gameObjects.getPlayer().move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                gameObjects.getPlayer().move(FIELD_SELL_SIZE, 0);
        }
        checkCompletion();
    }
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }
    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) return true;
                for (Box bb : gameObjects.getBoxes()) {
                    if (box.isCollision(bb, direction)) return true;
                }
                switch (direction) {
                    case UP:
                        box.move(0, -FIELD_SELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, FIELD_SELL_SIZE);
                        break;
                    case LEFT:
                        box.move(-FIELD_SELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_SELL_SIZE, 0);
                }
            }
        }
        return false;
    }
    public void checkCompletion() {
        boolean levelCompleted = true;
        for (Box box : gameObjects.getBoxes()) {
            boolean matched = false;
            for (Home home : gameObjects.getHomes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                levelCompleted = false;
                break;
            }
        }
        if (levelCompleted) eventListener.levelCompleted(currentLevel);
    }
}
