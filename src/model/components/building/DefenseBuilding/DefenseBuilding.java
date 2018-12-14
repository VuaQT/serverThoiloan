package model.components.building.DefenseBuilding;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import model.components.building.Building;
import util.Key;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class DefenseBuilding extends Building {
    int defenseType;
    public DefenseBuilding(int id, int defenseType) {
        super(id, GameConfig.AreaType.DEFENSE);
        if(defenseType <0 || defenseType > GameConfig.DEFENSE.getDEF().size()){
            Debug.warn("DefenseBuilding Constructor : invalid defenseType" + defenseType);
            this.defenseType = 1;
        }   else    {
            this.defenseType = defenseType;
        }
    }

    public int getDefenseType() {
        return defenseType;
    }

    public int getMaxLevel(){
        return GameConfig.DEFENSE.getDEF().get(defenseType-1).size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.DEFENSE.getDEF().get(defenseType-1).get(this.upgradingLevel -1).getBuildTime();
    }

    public Key getSize(){
        return new Key(GameConfig.DEFENSE.getDEF().get(defenseType-1).get(this.currentLevel-1).getWidth(),GameConfig.DEFENSE.getDEF().get(defenseType-1).get(this.currentLevel-1).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(defenseType);
    }
}
