package model.components.building.MilitaryBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;
import util.Key;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class ArmyCamp extends Building {
    public ArmyCamp(int id) {
        super(id, GameConfig.AreaType.ARMY_CAMP);
    }
    public int getMaxLevel(){
        return GameConfig.ARMYCAMP.getAMC1().size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.ARMYCAMP.getAMC1().get(this.upgradingLevel-1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.ARMYCAMP.getAMC1().get(this.currentLevel-1).getWidth(),GameConfig.ARMYCAMP.getAMC1().get(this.currentLevel-1).getWidth());
    }

}
