package com.mateifmandache.hungryhenry;

import java.util.ArrayList;
import java.util.List;

public class FoodFactory {
    public static Food createFood(int row, int column, String stringCode) {
        Position position = Position.fromCell(row, column);
        List<RelativePosition> relativeCatchPositions
                = FoodDictionaries.relativeCatchPositions.get(stringCode);
        List<Position> catchPositions = new ArrayList<>();
        for (RelativePosition relativePosition : relativeCatchPositions) {
            catchPositions.add(position.apply(relativePosition));
        }
        int nutrition = FoodDictionaries.nutritions.get(stringCode);
        return new Food(stringCode, position, catchPositions, nutrition);
    }
}
