package com.mateifmandache.hungryhenry;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SampleLevelsetMaker {

    public static void main(String[] args) {
        Level level1 = new Level();
        level1.setObject(1, 1, StringCodes.HENRY + StringCodes.NO_DIRECTION);
        JSONArray levelset = new JSONArray();
        levelset.add(level1.toJSON());
        File file = new File("./res/test.json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(levelset.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
