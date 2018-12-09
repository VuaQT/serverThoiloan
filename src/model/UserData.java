package model;

import GameConfig.GameConfig;
import model.components.Area;
import model.components.Map;
import model.components.MilitaryStatus;
import util.database.DataModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CPU11630_LOCAL on 12/9/2018.
 */
public class UserData extends DataModel {
    int id;
    HashMap<Integer, Area> objectById ;
    Map map;
    MilitaryStatus militaryStatus;
    int amountAvailableBuilder; // not save in Database

    public UserData(int id){
        this.id = id;
        objectById = new HashMap<Integer, Area>();
        map = new Map();
        militaryStatus = new MilitaryStatus();
        amountAvailableBuilder = GameConfig.INIT_GAME.getPlayer().getBuilderNumber();
    }

    void updateAllArea(){
        // update all building, obstacles, military each time user login

    }
    void updateMilitary(){

    }

    Area getAreaById(int id){
        return this.objectById.getOrDefault(id,null);
    }

    ArrayList<Area> getObjectByType(int typeLevel1, int typeLevel2){
        ArrayList<Area> ans = new ArrayList<Area>();

        return ans;
    }

    int getCapacity(int type){

        return 0;
    }

    int getLevelTownHall(){

        return 1;
    }

    boolean addObject(int id, Area object){

        return true;
    }
}
