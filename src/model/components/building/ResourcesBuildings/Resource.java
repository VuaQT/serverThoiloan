package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;
import util.TimeManager;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Resource extends Building {
    int resourceType;
    int harvestMoment;
    public Resource(int id, int resourceType) {
        super(id, GameConfig.AREA_TYPE.RESOURCE);
        this.resourceType = resourceType;
        this.harvestMoment = TimeManager.getTime();
    }

    public void updateStatus(){

    }

    public int harvest(){
        int intervalTime =  TimeManager.getTime() - this.harvestMoment;
        this.harvestMoment = TimeManager.getTime();
        return intervalTime; //  the amount of resource
    }
    public int getResourceType() {
        return resourceType;
    }
}
