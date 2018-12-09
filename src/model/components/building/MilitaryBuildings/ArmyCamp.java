package model.components.building.MilitaryBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class ArmyCamp extends Building {
    public ArmyCamp(int id) {
        super(id, GameConfig.AREA_TYPE.ARMY_CAMP);
    }
}
