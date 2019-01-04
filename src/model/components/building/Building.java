package model.components.building;

import bitzero.util.common.business.Debug;
import model.components.Area;
import model.components.building.ResourcesBuildings.Resource;
import util.Key;
import util.ResourceType;
import util.TimeManager;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Building extends Area {
    protected int currentLevel;
    protected int upgradingLevel; // (0: not building, not upgrading), (1:constructing), (>1:upgrading)
    protected int upgradedMoment; // upgradedMoment of latest Building or Upgrading

    public Building(int id, int type) {
        super(id, type);
        this.currentLevel = 1;
        this.upgradingLevel = 1;
        this.upgradedMoment = TimeManager.getTime();
    }

    public boolean updateStatus(){
        // return true if a builder is working on
        if(this.upgradingLevel >0) {
            // upgrading/constructing
            if(this.upgradingLevel > this.getMaxLevel()){
                Debug.warn("Building updateStatus fail:" + this.getType() + " error upgradingLevel > maxLevel");
            }   else if(TimeManager.getTime()-this.upgradedMoment >= this.getCurrentBuildTime()){
                this.currentLevel = this.upgradingLevel;
                this.upgradedMoment = 0;
                this.upgradingLevel = 0;
                // a builder is released
                return false;
            }   else    {
                // a builder is working on
                return true;
            }
        }
        return false;
    }
    public boolean startUpgrade(){
        //this.updateStatus();
        if(this.upgradingLevel != 0){
            // upgrading
            return false;
        }
        if(this.currentLevel <=0 || this.currentLevel >= this.getMaxLevel()){
            // can't upgrade because currentLevel is maxLevel
            return false;
        }
        this.upgradingLevel = this.currentLevel+1;
        this.upgradedMoment = TimeManager.getTime();
        return true;
    }
    public boolean finishUpgrade(){
        // return true if success
        return this.actionUpgrade(1);
    }

    public boolean stopUpgrade(){
        // return true if success
        return this.actionUpgrade(0);
    }

    private boolean actionUpgrade(int type){
        // type = 1 : finishUpgradeNow , type = 0 : stopUpgradeNow
        //this.updateStatus();
        if(this.upgradingLevel ==0){
            //not upgrading
            return false;
        }
        if(type == 1) {
            this.currentLevel = this.upgradingLevel;
        }
        this.upgradingLevel = 0;
        this.upgradedMoment = 0;
        return true;
    }

    public int getMaxLevel(){
        // default : cannot upgrade because maxLevel = 1 (builderHut)
        return 1;
    }
    public int getCurrentBuildTime(){
        // default : buildTime = 0 (builderHut)
        return 0;
    }

    public ResourceType getUpgradeResourceRequire(int level){
        return null;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getUpgradingLevel() {
        return upgradingLevel;
    }

    public Key getSize(){
        return new Key(0,0);
    }

    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(currentLevel);
        currentByteBuffer.putInt(upgradingLevel);
        currentByteBuffer.putInt(upgradedMoment);
    }
    public int getLevelTownHallRequiredToUpgrade(){
        return 1;
    }

    public String toString(){
        String s = super.toString() + " currentLevel : " + this.currentLevel + " upgradingLevel : " + upgradingLevel + "  upgradedMoment : " + upgradedMoment;
        return s;
    }
}
