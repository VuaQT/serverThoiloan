package model;

import model.components.Area;
import model.components.MilitaryStatus;
import util.database.DataModel;

import java.util.HashMap;

/**
 * Created by Trungnq4 on 12/13/2018.
 */
public class UserDataModel extends DataModel{
    private int idCounter = 1;
    public int id;    // save to DB                                     //  1                     1
    private HashMap<Integer,Integer> mapIdToClassType;    // save to DB         //  2
    private HashMap<Integer,String> mapIdToJsonString; // save to DB            //  3

}
