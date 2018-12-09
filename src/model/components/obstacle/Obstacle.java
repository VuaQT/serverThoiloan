package model.components.obstacle;

import GameConfig.GameConfig;
import model.components.Area;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Obstacle extends Area {
    public Obstacle(int id) {
        super(id, GameConfig.AREA_TYPE.OBSTACLE);
    }
}
