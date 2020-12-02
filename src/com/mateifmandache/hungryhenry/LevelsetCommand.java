package com.mateifmandache.hungryhenry;

public class LevelsetCommand extends Command {
    private final String name;
    public LevelsetCommand(String name) {
        super(CommandType.START_LEVELSET);
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
