package model.components.building.MilitaryBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;
import util.Key;
import util.ResourceType;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Laboratory extends Building {
    List<Integer> levelArmy;
    List<Integer> levelDarkArmy;
    List<Integer> levelSorcery;
    // length of each list correspond to number of unlock type, for example: if the level of town hall is 2, there are 2 unlock army type => length of levelArmy = 2
    int investigatedMoment; // latest upgradedMoment investigate
    int investigatingId;    // id of the type investigating, investigatedId = 0 if not investigating,


    public Laboratory(int id) {
        super(id, GameConfig.AreaType.LABORATORY);
    }

    public int getMaxLevel(){
        return GameConfig.LABORATORY.getLAB1().size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.LABORATORY.getLAB1().get(this.currentLevel-1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.LABORATORY.getLAB1().get(this.currentLevel-1).getWidth(),GameConfig.LABORATORY.getLAB1().get(this.currentLevel-1).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        // pack Lab infor later
    }
    public ResourceType getUpgradeResourceRequire(int level){
        return new ResourceType(0,GameConfig.LABORATORY.getLAB1().get(level-1).getElixir(),GameConfig.LABORATORY.getLAB1().get(level-1).getDarkElixir(),0);
    }
    public int getLevelTownHallRequiredToUpgrade(){
        return GameConfig.LABORATORY.getLAB1().get(this.upgradingLevel -1).getTownHallLevelRequired() ;
    }
}
