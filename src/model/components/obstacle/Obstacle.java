package model.components.obstacle;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import model.components.Area;
import util.Key;
import util.TimeManager;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Obstacle extends Area {
    int cleanMoment;
    int obstacleType;
    public Obstacle(int id, int obstacleType) {
        super(id, GameConfig.AreaType.OBSTACLE);
        this.cleanMoment = 0;
        // cleanMoment = 0 => not click clean yet
        // cleanMoment = -1 => finish Clean
        if(obstacleType <0 || obstacleType > GameConfig.OBSTACLE.getOBS().size()){
            Debug.warn("Obstacle Constructor : invalid defenseType" + obstacleType);
            this.obstacleType = 1;
        }   else    {
            this.obstacleType = obstacleType;
        }
    }
    public boolean updateStatus(){
        // return true if a builder is working on
        if(cleanMoment <= 0) return false;
        if(TimeManager.getTime()-this.cleanMoment > GameConfig.OBSTACLE.getOBS().get(obstacleType-1).getBuildTime()){
            cleanMoment = -1;
            // finish clean
            return false;
        }   else    {
            return false;
        }
    }

    public void clean(){
        cleanMoment = -1;
    }
    public Key getSize(){
        return new Key(GameConfig.OBSTACLE.getOBS().get(this.obstacleType-1).getWidth(),GameConfig.OBSTACLE.getOBS().get(this.obstacleType-1).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
        currentByteBuffer.putInt(cleanMoment);
        currentByteBuffer.putInt(obstacleType);
    }
}
