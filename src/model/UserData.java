package model;

import GameConfig.GameConfig;
import model.components.Area;
import model.components.Map;
import model.components.MilitaryStatus;
import util.database.DataModel;

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

}
