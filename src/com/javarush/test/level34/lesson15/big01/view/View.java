package com.javarush.test.level34.lesson15.big01.view;
import com.javarush.test.level34.lesson15.big01.controller.Controller;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.model.Wall;

import javax.swing.*;
import java.util.Set;
/**
 * Created by Igor on 20.05.2016.
 */
public class View extends JFrame {
    private Controller controller;
    private Field field;
    public View(Controller controller) {
        this.controller = controller;
    }
    public void init() {
        field = new Field(this);
        add(field);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(getGameWidth(getGameObjects()), getGameHeight(getGameObjects()));
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }
    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }
    public void update() {
        field.repaint();
    }
    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }
    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(this, "Level " + level + " completed!");
        controller.startNextLevel();
        setSize(getGameWidth(getGameObjects()), getGameHeight(getGameObjects()));
    }
    private int getGameWidth(GameObjects gameObjects) {
        Set<Wall> walls = gameObjects.getWalls();
        int x = 0;
        for (Wall wall : walls) {
            if (x < wall.getX()) x = wall.getX();
        }
        return x + Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2;
    }
    private int getGameHeight(GameObjects gameObjects) {
        Set<Wall> walls = gameObjects.getWalls();
        int y = 0;
        for (Wall wall : walls) {
            if (y < wall.getY()) y = wall.getY();
        }
        return y + Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2 + 20;
    }
}
