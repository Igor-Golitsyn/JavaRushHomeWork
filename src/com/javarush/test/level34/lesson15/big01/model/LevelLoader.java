package com.javarush.test.level34.lesson15.big01.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Игорь on 23.05.2016.
 */
public class LevelLoader {
    private Path levels;
    public LevelLoader(Path levels) {
        this.levels = levels;
    }
    public GameObjects getLevel(int level) {
        if (level > 60) {
            level = level - (level / 60 * 60) == 0 ? 1 : level - (level / 60 * 60);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (true) {
                String line = reader.readLine();
                if (line.startsWith("Maze")) {
                    String[] mazeElements = line.split(":");
                    if (Integer.parseInt(mazeElements[1].trim()) == level) {
                        return scanObjects(reader);
                    }
                }
            }
        }
        catch (Exception e) {
        }
        return null;
    }
    private GameObjects scanObjects(BufferedReader reader) {
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int emptyLines = 0;
        int y = 0;
        while (emptyLines < 2) {
            String line = null;
            try {
                line = reader.readLine();
            }
            catch (IOException e) {
            }
            if (line.isEmpty()) {
                emptyLines++;
                y = 0;
                continue;
            }
            if (emptyLines == 1) {
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int xForObj = Model.FIELD_SELL_SIZE / 2 + i * Model.FIELD_SELL_SIZE;
                    int yForObj = Model.FIELD_SELL_SIZE / 2 + y * Model.FIELD_SELL_SIZE;
                    switch (chars[i]) {
                        case 'X':
                            walls.add(new Wall(xForObj, yForObj));
                            break;
                        case '*':
                            boxes.add(new Box(xForObj, yForObj));
                            break;
                        case '.':
                            homes.add(new Home(xForObj, yForObj));
                            break;
                        case '&': {
                            homes.add(new Home(xForObj, yForObj));
                            boxes.add(new Box(xForObj, yForObj));
                        }
                        break;
                        case '@':
                            player = new Player(xForObj, yForObj);
                    }
                }
            }
            y++;
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
