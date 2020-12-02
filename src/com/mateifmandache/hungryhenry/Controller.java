package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final JLayeredPane window;
    private final JFrame frame;
    private List<Activity> activityStack = new ArrayList<>();

    private Activity popActivity() {
        return activityStack.remove(activityStack.size() - 1);
    }

    private Activity currentActivity() {
        return activityStack.get(activityStack.size() - 1);
    }

    private void addActivity(Activity a) {
        activityStack.add(a);
    }

    public Controller(JLayeredPane window, JFrame frame) {
        this.window = window;
        this.frame = frame;
    }
    public void start() {
        Activity firstActivity = new StartMenuActivity(window, this);
        addActivity(firstActivity);
        firstActivity.enter();
        System.out.println("Game started");
    }
    public void invoke(Command command) {
        switch (command.type) {
            case EXIT_GAME -> exit();
            case START_LEVELSET -> startLevelset(command);
            case START_LEVEL -> startLevel(command);
        }
    }
    private void exit() {
        while (activityStack.size() > 0) {
            popActivity().exit();
        }
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    private void startLevelset(Command command) {
        Activity playLevelsetActivity =
                new PlayLevelsetActivity(((LevelsetCommand) command).getName(),this);
        addActivity(playLevelsetActivity);
        playLevelsetActivity.enter();
    }
    private void startLevel(Command command) {
        Activity levelActivity = new LevelActivity(window, this);
        addActivity(levelActivity);
        levelActivity.enter();
    }
}
