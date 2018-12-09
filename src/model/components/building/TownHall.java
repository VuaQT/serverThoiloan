package model.components.building;

import GameConfig.GameConfig;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class TownHall extends Building {
    public TownHall(int id) {
        super(id, GameConfig.AREA_TYPE.TOWN_HALL);
    }
}
