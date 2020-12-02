package com.mateifmandache.hungryhenry;

import org.json.simple.*;

import java.util.Arrays;

public class Level {
    private int hunger;
    private boolean timed;
    private int time;
    private String[][] objects;
    public Level() {
        hunger = 1;
        timed = false;
        time = 60;
        objects = new String[Constants.LEVEL_HEIGHT][Constants.LEVEL_WIDTH];
        for (int i = 0; i < Constants.LEVEL_HEIGHT; i++) {
            for (int j = 0; j < Constants.LEVEL_WIDTH; j++) {
                objects[i][j] = StringCodes.SPACE + StringCodes.NO_DIRECTION;
            }
        }
    }
    public Level(JSONObject data) {
        hunger = (int) (long) data.get("hunger");
        timed = (boolean) data.get("timed");
        time = (int) (long) data.get("time");
        objects = new String[Constants.LEVEL_HEIGHT][Constants.LEVEL_WIDTH];
        JSONArray array = (JSONArray) data.get("objects");
        for (int i = 0; i < Constants.LEVEL_HEIGHT; i++) {
            JSONArray subArray = (JSONArray) array.get(i);
            for (int j = 0; j < Constants.LEVEL_WIDTH; j++) {
                objects[i][j] = (String) subArray.get(j);
            }
        }
    }
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("hunger", hunger);
        obj.put("timed", timed);
        obj.put("time", time);
        JSONArray array = new JSONArray();
        for (int i = 0; i < Constants.LEVEL_HEIGHT; i++) {
            JSONArray subArray = new JSONArray();
            subArray.addAll(Arrays.asList(objects[i]));
            array.add(subArray);
        }
        obj.put("objects", array);
        return obj;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public int getHunger() {
        return hunger;
    }
    public void setTimed(boolean timed) {
        this.timed = timed;
    }
    public boolean getTimed() {
        return timed;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getTime() {
        return time;
    }
    public void setObject(int x, int y, String object) {
        objects[y][x] = object;
    }
    public String getObject(int x, int y) {
        return objects[y][x];
    }
}
