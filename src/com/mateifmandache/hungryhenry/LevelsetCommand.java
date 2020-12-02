package com.mateifmandache.hungryhenry;

public class LevelsetCommand extends Command {
    public final String name;
    public LevelsetCommand(String name) {
        super(CommandType.START_LEVELSET);
        this.name = name;
    }
}
