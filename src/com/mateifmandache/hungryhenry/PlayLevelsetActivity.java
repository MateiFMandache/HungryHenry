package com.mateifmandache.hungryhenry;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayLevelsetActivity implements Activity {
    private final String levelsetName;
    private JSONArray levelset;
    private int levelNumber;
    private Controller controller;
    public PlayLevelsetActivity(String levelsetName, Controller controller) {
        this.levelsetName = levelsetName;
        this.controller = controller;
    }
    @Override
    public void enter() {
        try {
            File levelsetFile = new File("./res/levelsets/" + levelsetName + ".json");
            Scanner scanner = new Scanner(levelsetFile);
            JSONParser parser = new JSONParser();
            levelset = (JSONArray) parser.parse(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Levelset not found");
            e.printStackTrace();
            return;
        } catch (ParseException pe) {
            System.out.println("Levelset file corrupted");
            System.out.println("Position: " + pe.getPosition());
            pe.printStackTrace();
            return;
        }
        levelNumber = 0;
        startCurrentLevel();
    }

    public void startCurrentLevel() {
        Map<String, Boolean> extras = new HashMap<>();
        extras.put("lastLevel", levelNumber == levelset.size() - 1);
        extras.put("fromEditor", false);
        JSONObject levelData = (JSONObject) levelset.get(levelNumber);
        controller.invoke(new StartLevelCommand(new Level(levelData), extras));
    }

    @Override
    public void exit() {

    }
}
