package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import model.components.building.Building;
import util.Key;
import util.ResourceType;
import util.TimeManager;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Resource extends Building {
    int resourceType;
    int harvestMoment;
    public Resource(int id, int resourceType) {
        super(id, GameConfig.AreaType.RESOURCE);
        if(resourceType <0 || resourceType > GameConfig.RESOURCE.getRES().size()){
            Debug.warn("Resource Constructor : invalid resourceType" + resourceType);
            this.resourceType = 1;
        }   else    {
            this.resourceType = resourceType;
        }
        this.harvestMoment = TimeManager.getTime();
    }

    public int harvest(){
        int intervalTime =  TimeManager.getTime() - this.harvestMoment;
        this.harvestMoment = TimeManager.getTime();
        return intervalTime; //  the amount of resource
    }
    public int getResourceType() {
        return resourceType;
    }
    public Key getSize(){
        return new Key(GameConfig.RESOURCE.getRES().get(resourceType-1).get(this.currentLevel-1).getWidth(),GameConfig.RESOURCE.getRES().get(resourceType-1).get(this.currentLevel-1).getHeight());
    }
    public int getCurrentBuildTime(){
        return GameConfig.RESOURCE.getRES().get(this.resourceType-1).get(this.currentLevel-1).getBuildTime();
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(resourceType);
        currentByteBuffer.putInt(harvestMoment);
    }
    public ResourceType getUpgradeResourceRequire(int level){
        return new ResourceType(GameConfig.RESOURCE.getRES().get(this.resourceType-1).get(level-1).getGold(),GameConfig.RESOURCE.getRES().get(this.resourceType-1).get(level-1).getElixir(),GameConfig.RESOURCE.getRES().get(this.resourceType-1).get(level-1).getDarkElixir(),0);
    }
}
