package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import model.components.building.Building;
import util.Key;
import util.ResourceType;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Storage extends Building{
    int storageType;
    public Storage(int id, int storageType) {
        super(id, GameConfig.AreaType.STORAGE);
        if(storageType <0 || storageType> GameConfig.STORAGE.getSTO().size()){
            Debug.warn("Storage Constructor : invalid storageType" + storageType);
            this.storageType = 1;
        }   else    {
            this.storageType = storageType;
        }
    }

    public int getStorageType() {
        return storageType;
    }
    public int getMaxLevel(){
        return GameConfig.STORAGE.getSTO().get(this.storageType-1).size();
    }
    public Key getSize(){
        return new Key(GameConfig.STORAGE.getSTO().get(storageType-1).get(this.currentLevel-1).getWidth(),GameConfig.STORAGE.getSTO().get(storageType-1).get(this.currentLevel-1).getHeight());
    }
    public int getCurrentBuildTime(){
        return GameConfig.STORAGE.getSTO().get(storageType-1).get(this.currentLevel-1).getBuildTime();
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(storageType);
    }
    public ResourceType getUpgradeResourceRequire(int level){
        return new ResourceType(GameConfig.STORAGE.getSTO().get(storageType-1).get(level-1).getGold(),GameConfig.STORAGE.getSTO().get(storageType-1).get(level-1).getElixir(),GameConfig.STORAGE.getSTO().get(storageType-1).get(level-1).getDarkElixir(),0);
    }
    public int getCapacity(){
        return GameConfig.RESOURCE.getRES().get(this.storageType-1).get(this.currentLevel-1).getCapacity();
    }
    public String toString(){
        return "Storage " + super.toString() + " storageType :" + storageType;
    }
    public int getLevelTownHallRequiredToUpgrade(){
        return GameConfig.STORAGE.getSTO().get(this.storageType-1).get(currentLevel).getTownHallLevelRequired() ;
    }
}
