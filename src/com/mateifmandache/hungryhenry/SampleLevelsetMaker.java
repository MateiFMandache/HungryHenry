package com.mateifmandache.hungryhenry;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SampleLevelsetMaker {

    public static void main(String[] args) {
        Level level1 = new Level();
        level1.setTimed(true);
        level1.setObject(1, 1, StringCodes.HENRY + StringCodes.NO_DIRECTION);
        level1.setObject(3, 1, StringCodes.WALL + StringCodes.NO_DIRECTION);
        level1.setObject(1, 3, StringCodes.WALL + StringCodes.NO_DIRECTION);
        level1.setObject(3, 3, StringCodes.BADDY4 + StringCodes.NO_DIRECTION);
        level1.setObject(4, 1, StringCodes.FOOD1 + StringCodes.NO_DIRECTION);
        level1.setObject(5, 1, StringCodes.FOOD2 + StringCodes.NO_DIRECTION);
        level1.setHunger(4);
        JSONArray levelset = new JSONArray();
        levelset.add(level1.toJSON());
        File file = new File("./res/levelsets/test.json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(levelset.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
