package model.components.building.MilitaryBuildings;

import java.util.List;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Laboratory extends MilitaryBuilding {
    List<Integer> levelArmy;
    List<Integer> levelDarkArmy;
    List<Integer> levelSorcery;
    // length of each list correspond to number of unlock type, for example: if the level of town hall is 2, there are 2 unlock army type => length of levelArmy = 2
    int investigatedMoment; // latest upgradedMoment investigate
    int investigatingId;    // id of the type investigating, investigatedId = 0 if not investigating,


    public Laboratory(int id) {
        super(id, 1);
    }

    public void updateStatus(){
        // update for building level and list length
        if(this.levelUpgrading >0){
//            if(TimeManager.getTime() - this.upgradedMoment >  ... ){
//
//            }
        }
    }
}
