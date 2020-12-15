package com.mateifmandache.hungryhenry;

import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    private double time;
    private int hunger;
    private boolean timed;
    private Wall wall;
    private Henry henry = null;
    private List<Baddy> baddies;
    private List<Food> foods;
    private LevelState levelState;
    private double countDownTime;
    public void load(Level data) {
        time = data.getTime();
        timed = data.getTimed();
        hunger = data.getHunger();
        wall = new Wall();
        baddies = new ArrayList<>();
        foods = new ArrayList<>();
        for (int row = 0; row < Constants.LEVEL_HEIGHT; row++) {
            for (int column = 0; column < Constants.LEVEL_WIDTH; column++) {
                String objectString = data.getObject(row, column);
                // first two characters indicate type of object
                String typeCode = objectString.substring(0, 2);
                switch (typeCode) {
                    case StringCodes.HENRY:
                        henry = new Henry(row, column);
                        break;
                    case StringCodes.WALL:
                        wall.addWall(row, column);
                        break;
                    case StringCodes.BADDY4:
                        baddies.add(new Baddy4(row, column));
                        break;
                    case StringCodes.FOOD1:
                    case StringCodes.FOOD2:
                    case StringCodes.FOOD3:
                    case StringCodes.FOOD4:
                    case StringCodes.FOOD5:
                    case StringCodes.FOOD6:
                    case StringCodes.FOOD7:
                    case StringCodes.FOOD8:
                    case StringCodes.FOOD9:
                        foods.add(FoodFactory.createFood(row, column, typeCode));
                }
            }
        }
        for (Baddy baddy : baddies) {
            baddy.setHenry(henry);
        }
        levelState = LevelState.COUNTDOWN;
        countDownTime = Constants.COUNTDOWN_TIME;
    }
    public void update() {
        switch (levelState) {
            case COUNTDOWN:
                countDownTime -= (double) Constants.MILLISECONDS_PER_FRAME
                                / (double) Constants.MILLISECONDS;
                if (countDownTime < 0.0) {
                    levelState = LevelState.PLAYING;
                }
                break;
            case PLAYING:
                henry.move(wall);
                for (Baddy baddy : baddies) {
                    baddy.move(wall);
                    if (baddy.catches()) {

                    }
                }
                List<Food> eatenFoods = new ArrayList<>();
                for (Food food : foods) {
                    if (food.catches(henry)) {
                        eatenFoods.add(food);
                    }
                }
                for (Food food : eatenFoods) {
                    hunger -= food.getNutrition();
                    foods.remove(food);
                }
                // don't allow hunger to go below 0
                hunger = Math.max(hunger, 0);

                if (timed) {
                    time -= (double) Constants.MILLISECONDS_PER_FRAME
                            / (double) Constants.MILLISECONDS;
                }
                break;
        }
    }
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(wall.getItems());
        for (Food food : foods) {
            items.add(food.toItem());
        }
        for (Baddy baddy : baddies) {
            items.add(baddy.toItem());
        }
        if (henry != null) {
            items.add(henry.toItem());
        }
        return items;
    }
    public boolean getCountDown() {
        return levelState == LevelState.COUNTDOWN;
    }
    public String getCountDownTime() {
        return Integer.toString((int) Math.ceil(countDownTime));
    }
    public int getTime() {
        return (int) time;
    }
    public int getHunger() {
        return hunger;
    }
    public boolean getTimed() {
        return timed;
    }
}
