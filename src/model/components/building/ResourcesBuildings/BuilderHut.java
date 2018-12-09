package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class BuilderHut extends Building {
    public BuilderHut(int id) {
        super(id, GameConfig.AREA_TYPE.BUILDER_HUT);
    }
}
