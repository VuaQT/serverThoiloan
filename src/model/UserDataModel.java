package model;

import model.components.Area;
import model.components.MilitaryStatus;
import util.database.DataModel;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Trungnq4 on 12/13/2018.
 */
public class UserDataModel extends DataModel{
    public int idCounter = 1;
    public int id;    // save to DB                                     //  1                     1
    public HashMap<Integer,Integer> mapIdToClassType;    // save to DB         //  2
    public HashMap<Integer,String> mapIdToJsonString; // save to DB            //  3
    // map
    public HashMap<Integer, Point> mapIdToPosition; // save to DB
    public UserDataModel(int uid){
        this.id = uid;
        mapIdToClassType = new HashMap<Integer,Integer>();
        mapIdToJsonString = new HashMap<Integer,String>();
        //map
        mapIdToPosition = new HashMap<Integer, Point>();
    }
}
