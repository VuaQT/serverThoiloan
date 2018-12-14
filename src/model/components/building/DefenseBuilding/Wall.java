package model.components.building.DefenseBuilding;

import GameConfig.GameConfig;
import model.components.building.Building;

/**
 * Created by CPU11630_LOCAL on 12/11/2018.
 */
public class Wall extends Building {

    public Wall(int id, int type) {
        super(id, type);
    }
    public int getMaxLevel(){
        return GameConfig.WALL.getWAL1().size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.WALL.getWAL1().get(this.currentLevel-1).getBuildTime();
    }
}

// temporary unused