package com.mateifmandache.hungryhenry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDictionaries extends HashMap<String, String> {
    public static final Map<String, String> names = new HashMap<>();
    public static final Map<String, Integer> nutritions = new HashMap<>();
    public static final Map<String, List<RelativePosition>> relativeCatchPositions
                                                                        = new HashMap<>();
    static {
        names.put(StringCodes.FOOD1, "cheese");
        nutritions.put(StringCodes.FOOD1, 2);
        List<RelativePosition> f1RelativeCatchPositions = new ArrayList<>();
        f1RelativeCatchPositions.add(new RelativePosition(0, 0));
        relativeCatchPositions.put(StringCodes.FOOD1, f1RelativeCatchPositions);

        names.put(StringCodes.FOOD2, "apple");
        nutritions.put(StringCodes.FOOD2, 1);
        List<RelativePosition> f2RelativeCatchPositions = new ArrayList<>();
        f2RelativeCatchPositions.add(new RelativePosition(0, 0));
        relativeCatchPositions.put(StringCodes.FOOD2, f2RelativeCatchPositions);
    }
}
