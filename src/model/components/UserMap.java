package model.components;

import GameConfig.GameConfig;
import bitzero.util.common.business.Debug;
import util.Key;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * Created by CPU11630_LOCAL on 12/9/2018.
 */
public class UserMap {
//    public int id;
    public HashMap<Integer, Point> mapIdToPosition; // save to DB
    public HashMap<Integer, Key> mapIdToSize; // not save to DB
    public boolean [][] mapGrid; // not save to DB
    // mapGrid and mapIdToSize is created each time user login,
    // mapGrid is used for checking if a position in map has already had object or not
    // mapGrid is not saved in DB and is not sent to Client

    public UserMap(HashMap<Integer, Point> mapIdToPosition){
        this.mapIdToPosition = mapIdToPosition;
    }

    public void loadEachLogin(HashMap<Integer, Area> mapIdToArea){
        mapIdToSize = new HashMap<Integer, Key>();
        mapGrid = new boolean[GameConfig.boardWidth][GameConfig.boardHeight];
        for(HashMap.Entry<Integer, Area> entry : mapIdToArea.entrySet()){
            int id = entry.getKey();
            Key size = entry.getValue().getSize();
            mapIdToSize.put(id, size);
            Point pos = mapIdToPosition.get(id);
            if(pos==null){
                Debug.warn("UserMap - loadEachLogin : cannot find object position id = " + id  + ", size =  " + size.first + " " + size.second);
            }   else    {
                try {
                    for(int i=0;i<size.first;i++){
                        for(int j=0;j<size.second;j++){
                            if(mapGrid[pos.x+i-1][pos.y+j-1]==false){
                                mapGrid[pos.x+i-1][pos.y+j-1]=true;
                            }   else    {
                                Debug.warn("UserMap - loadObjectSizeAndMapGrid : Area OverLap each other");
                            }
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean addObject(Area area, Point pos){
        // update info of mapIdToPosition, mapIdToSize and mapGrid
        //...
        Key size = area.getSize();
        // check again
        if(!checkIfFreeSpace(pos,size)){
            return false;
        }
        // update mapIdToPosition
        this.mapIdToPosition.put(area.getId(), pos);
        // update mapIdToSize
        this.mapIdToSize.put(area.getId(), size);
        // update mapGrid
        for(int i=0;i<size.first;i++){
            for(int j=0;j<size.second;j++){
                mapGrid[pos.x+i-1][pos.y+j-1]=true;
            }
        }
        return true;    // success
    }

    public boolean checkIfFreeSpace(Point pos, Key size){
        try {
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    if(mapGrid[pos.x+i-1][pos.y+j-1]==true){
                        return false;
                    }
                }
            }
            return true;
        }catch (Exception e){
            Debug.warn(pos + " <" + size.first + "," + size.second + ">");
            return false;
        }
    }

    public boolean checkIfFreeSpaceToMove(int id, Point pos){
        Key size = this.mapIdToSize.get(id);
        boolean[][] tempGrid = new boolean[size.first][size.second];
        try {
            // backup grid
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    tempGrid[i][j] = mapGrid[pos.x+i-1][pos.y+j-1];
                    mapGrid[pos.x+i-1][pos.y+j-1] = false;
                }
            }
            // check
            boolean ans = checkIfFreeSpace(pos,size);
            // restore grid
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    mapGrid[pos.x+i-1][pos.y+j-1] = tempGrid[i][j];
                }
            }
            return ans;
        }catch (Exception e){
            Debug.warn(pos + " <" + size.first + "," + size.second + ">");
            e.printStackTrace();
            return false;
        }
    }

    public boolean moveObject(int id, Point newPos){
        // update info of mapIdToPosition and mapGrid
        //...
        try {
            Key size = this.mapIdToSize.get(id);
            Point pos = this.mapIdToPosition.get(id);
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    mapGrid[pos.x+i-1][pos.y+j-1] = false;
                }
            }
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    mapGrid[newPos.x+i-1][newPos.y+j-1] = true;
                }
            }
            this.mapIdToPosition.replace(id,newPos);
            return false;
        }   catch (Exception e){
            return true;
        }
    }
    public boolean deleteObject(int id){
        // update info of mapIdToPosition, mapIdToSize and mapGrid
        //...
        Point pos = this.mapIdToPosition.get(id);
        Key size = this.mapIdToSize.get(id);
        this.mapIdToPosition.remove(id);
        this.mapIdToSize.remove(id);
        try {
            for(int i=0;i<size.first;i++){
                for(int j=0;j<size.second;j++){
                    mapGrid[pos.x+i-1][pos.y+j-1]=false;
                }
            }
        }   catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void showMap(){
        Debug.info("UserMap - showMap:");
        String s="     ";
        for(int j=0;j<GameConfig.boardHeight;j++){
            s += (j+1)%10;
        }
        Debug.info(s);
        Debug.info("    ----------------------------------------");
        for (int i=0;i<GameConfig.boardWidth;i++){
            s = "  " + ((i+1)%10) + " |";
            for(int j=0;j<GameConfig.boardHeight;j++){
                if(mapGrid[i][j]==true){
                    s += "1";
                }   else {
                    s += "0";
                }
            }
            Debug.info(s);
        }
    }
}
