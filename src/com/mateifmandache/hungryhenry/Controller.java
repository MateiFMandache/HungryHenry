package com.mateifmandache.hungryhenry;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final JFrame window;
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

    public Controller(JFrame window) {
        this.window = window;
    }
    public void start() {
        Activity firstActivity = new StartMenuActivity(window, this);
        addActivity(firstActivity);
        firstActivity.enter();
        System.out.println("Game started");
    }
    public void invoke(Command command) {
        switch (command.type) {
            case EXIT_GAME:
                exit();
        }
    }
    private void exit() {
        while (activityStack.size() > 0) {
            popActivity().exit();
        }
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
