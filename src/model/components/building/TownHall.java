package model.components.building;

import GameConfig.GameConfig;
import util.Key;
import util.ResourceType;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class TownHall extends Building {
    public TownHall(int id) {
        super(id, GameConfig.AreaType.TOWN_HALL);
    }

    public int getMaxLevel(){
        return GameConfig.TOWN_HALL.getTOW1().size();
    }
    public int getCurrentBuildsTime(){
        return GameConfig.TOWN_HALL.getTOW1().get(upgradingLevel -1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel).getWidth(),GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
    }
    public ResourceType getUpgradeResourceRequire(int level){
        return new ResourceType(GameConfig.TOWN_HALL.getTOW1().get(level-1).getGold(),0,GameConfig.TOWN_HALL.getTOW1().get(level-1).getDarkElixir(),0);
    }
    public int getCapacity(int type){
        switch (type){
            case 1:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityGold();
            case 2:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityElixir();
            case 3:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityDarkElixir();
        }
        return 0;
    }
}
