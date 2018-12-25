package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;
import util.Key;
import util.ResourceType;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class BuilderHut extends Building {
    int order;
    public BuilderHut(int id, int order) {
        super(id, GameConfig.AreaType.BUILDER_HUT);
        this.order = order;
    }
//    public int getMaxLevel(){
//        return 1;
//    }
//    public int getCurrentBuildTime(){
//        return 0;
//    }
    public Key getSize(){
        return new Key(GameConfig.BUILDERHUT.getBDH1().get(this.currentLevel-1).getWidth(),GameConfig.BUILDERHUT.getBDH1().get(this.currentLevel-1).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
//        currentByteBuffer.putInt(order);
    }
    public ResourceType getUpgradeResourceRequire(int order){
        return new ResourceType(0,0,0,GameConfig.BUILDERHUT.getBDH1().get(order-1).getCoin());
    }
    public String toString(){
        return "BuliderHut "+ super.toString() + " order " + order;
    }
}
