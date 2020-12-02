package com.mateifmandache.hungryhenry;

import java.util.Map;

public class StartLevelCommand extends ExtraCommand {
    private final Level level;
    public StartLevelCommand(Level level, Map<String, Boolean> extras) {
        super(CommandType.START_LEVEL, extras);
        this.level = level;
    }
    public Level getLevel() {
        return this.level;
    }
}
