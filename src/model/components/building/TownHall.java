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
    public int getCurrentBuildTime(){
        return GameConfig.TOWN_HALL.getTOW1().get(upgradingLevel -1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel).getWidth(),GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
    }
    public ResourceType getUpgradeResourceRequire(int level){
        return new ResourceType(GameConfig.TOWN_HALL.getTOW1().get(level-1).getGold(),0,GameConfig.TOWN_HALL.getTOW1().get(level-1).getDarkElixir());
    }
    public int getCapacity(int type){
        switch (type){
            case GameConfig.ResourceType.GOLD:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityGold();
            case GameConfig.ResourceType.ELIXIR:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityElixir();
            case GameConfig.ResourceType.DARK_ELIXIR:
                return GameConfig.TOWN_HALL.getTOW1().get(this.currentLevel-1).getCapacityDarkElixir();
        }
        return 0;
    }
    public String toString(){
        return "TownHall " + super.toString();
    }
}
