package model.components.building.MilitaryBuildings;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import model.components.building.Building;
import util.Key;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Barrack extends Building {
    int barrackType;
    TrainingProcess trainingProcess;
    public Barrack(int id, int barrackType) {
        super(id, GameConfig.AreaType.BARRACK);
        trainingProcess = new TrainingProcess();
        if(barrackType <0 || barrackType > GameConfig.BARRACK.getBAR().size()){
            Debug.warn("Barrack Constructor : invalid barrackType" + barrackType);
            this.barrackType = 1;
        }   else    {
            this.barrackType = barrackType;
        }
    }
    public int getMaxLevel(){
        return GameConfig.BARRACK.getBAR().get(barrackType-1).size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.BARRACK.getBAR().get(barrackType-1).get(currentLevel-1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.BARRACK.getBAR().get(this.barrackType-1).get(this.currentLevel-1).getWidth(),GameConfig.BARRACK.getBAR().get(this.barrackType-1).get(this.currentLevel-1).getHeight());
    }

    // build 2
    public boolean addTraining(int amount, int type){
        // return true if success

        return true;
    }
    public boolean finishTraining(){

        return true;
    }

    public TrainingProcess getTrainingProcess() {
        return trainingProcess;
    }

    public int getBarrackType() {
        return barrackType;
    }

    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(barrackType);
    }
}
