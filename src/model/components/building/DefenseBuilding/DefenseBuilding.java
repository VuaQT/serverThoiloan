package model.components.building.DefenseBuilding;

import GameConfig.GameConfig;
import model.components.building.Building;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class DefenseBuilding extends Building {
    int defenseType;
    public DefenseBuilding(int id, int defenseType) {
        super(id, GameConfig.AREA_TYPE.DEFENSE);
        this.defenseType = defenseType;
    }
}
