package model.components.building.MilitaryBuildings;

import GameConfig.GameConfig;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Barrack extends MilitaryBuilding {
    TrainingProcess trainingProcess;
    public Barrack(int id, int type) {
        super(id, GameConfig.AREA_TYPE.BARRACK);
        trainingProcess = new TrainingProcess();
    }
    public void  updateStatus(){
        // update upgrading status

    }


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
}


//        queue.add(1);
//        for (Integer item: queue) {
//            System.out.println(item);
//        }
