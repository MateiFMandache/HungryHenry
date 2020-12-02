package com.mateifmandache.hungryhenry;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;
import java.util.Scanner;

public class PlayLevelsetActivity implements Activity {
    private String levelsetName;
    private JSONArray levelset;
    public PlayLevelsetActivity(String levelsetName) {
        this.levelsetName = levelsetName;
    }
    @Override
    public void enter() {
        try {
            File levelsetFile = new File("./res/" + levelsetName + ".json");
            Scanner scanner = new Scanner(levelsetFile);
            JSONParser parser = new JSONParser();
            levelset = (JSONArray) parser.parse(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Levelset not found");
            e.printStackTrace();
        } catch (ParseException pe) {
            System.out.println("Levelset file corrupted");
            System.out.println("Position: " + pe.getPosition());
            pe.printStackTrace();
        }
    }

    @Override
    public void exit() {

    }
}
