package model.components;

import GameConfig.GameConfig;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by CPU11630_LOCAL on 12/9/2018.
 */
public class Map {
    int id;
    HashMap<Integer, Point> objectPosition = new HashMap<Integer, Point>();
    boolean [][] mapGrid = new boolean[GameConfig.boardWidth][GameConfig.boardHeight];
    // mapGrid is created each time user login,
    // mapGrid is used for checking if a position in map has already had object or not
    // mapGrid is not saved in DB and is not sent to Client
    public Map(){

    }
    boolean addObject(int id, Point pos){

        return true;    // if valid
    }
    boolean moveObject(int id, Point newPos){

        return true;
    }
    boolean deleteObject(int id){

        return true;
    }

}
