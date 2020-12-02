package com.mateifmandache.hungryhenry;

import java.util.Map;

public class ExtraCommand extends Command {
    public Map<String, Boolean> extras;
    public ExtraCommand(CommandType type, Map<String, Boolean> extras) {
        super(type);
        this.extras = extras;
    }
    public boolean getExtra(String extra) {
        return extras.get(extra);
    }
}
