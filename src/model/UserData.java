    package model;

import GameConfig.GameConfig;
import GameConfig.InitGame.Ob;
import bitzero.util.common.business.Debug;
import com.google.gson.Gson;
import model.components.Area;
import model.components.UserMap;
import model.components.building.Building;
import model.components.building.ClanCastle;
import model.components.building.DefenseBuilding.DefenseBuilding;
import model.components.building.MilitaryBuildings.ArmyCamp;
import model.components.building.MilitaryBuildings.Barrack;
import model.components.building.MilitaryBuildings.Laboratory;
import model.components.building.ResourcesBuildings.BuilderHut;
import model.components.building.ResourcesBuildings.Resource;
import model.components.building.ResourcesBuildings.Storage;
import model.components.building.TownHall;
import model.components.obstacle.Obstacle;
import util.Key;
import util.database.DataModel;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * Created by Trungnq4 on 12/9/2018.
 */
public class UserData{
    //private int idCounter = 1;
    static final Gson gson = new Gson();                                //save  unsave       sendToClient
    //public int id;    // save to DB                                     //  1                     1
    private HashMap<Integer, Area> mapIdToArea;  // not save to DB       //           1            2
    //private HashMap<Integer,Integer> mapIdToClassType;    // save to DB         //  2
    //private HashMap<Integer,String> mapIdToJsonString; // save to DB            //  3
    public UserMap userMap; // save partly to DB, send partly           //  4    2(2.1,2.2)       3
    //MilitaryStatus militaryStatus;  // save to DB                       //  5
    private HashMap<Key,HashSet<Integer>> mapTypeToIds; // not save to DB     //         3
    private HashSet<Area> builderWorkingAreas;                           //         4

    UserDataModel dataModel;

    public void save(int uId) throws Exception{
        Debug.info("saveModel UserData with uId = " + uId);
        this.dataModel.saveModel(uId);
    }

    public static UserData get(int uId) throws Exception{
        Debug.info("getModel UserData with uId = " + uId);
        UserDataModel userDataModel = (UserDataModel) DataModel.getModel(uId,UserDataModel.class);
        UserData userData;
        if(userDataModel == null){
            userDataModel = new UserDataModel(uId);
            userData = new UserData(userDataModel);
            userData.updateAfterGetModel();
            userData.createInitArea();
            userData.save(uId);
            return userData;
        }
        userData = new UserData(userDataModel);
        userData.updateAfterGetModel();
        return userData;
    }

    public UserData(UserDataModel userDataModel){
        //militaryStatus = new MilitaryStatus();
        this.dataModel = userDataModel;
        this.userMap = new UserMap(userDataModel.mapIdToPosition);
        this.updateAfterGetModel();
    }

    private void createInitArea(){
        // init areas for new user
        // init building
        Debug.info("createInitArea");
        this.createInitArea(new Key(GameConfig.AreaType.TOWN_HALL, 0), new Point(20, 19));
        this.createInitArea(new Key(GameConfig.AreaType.BUILDER_HUT, 0), new Point(18, 21));
        this.createInitArea(new Key(GameConfig.AreaType.BARRACK, 1), new Point(30, 22));
//      for test working builder
        this.createInitArea(new Key(GameConfig.AreaType.ARMY_CAMP, 0), new Point(20, 24));
        this.createInitArea(new Key(GameConfig.AreaType.RESOURCE, 1), new Point(25, 19));
        // init obstacles
//        this.createInitArea(new Key(GameConfig.AreaType.LABORATORY, 0), new Point(37, 30));
//        this.createInitArea(new Key(GameConfig.AreaType.STORAGE, 1), new Point(9, 25));
//        this.createInitArea(new Key(GameConfig.AreaType.CLAN_CASTLE, 0), new Point(32, 36));
//        this.createInitArea(new Key(GameConfig.AreaType.DEFENSE, 6), new Point(17,35));


        java.util.List<Ob> obs = GameConfig.INIT_GAME.getObs();
        for (int i = 0; i < 2; i++) { // obs.size()
            String typeString = obs.get(i).getType();
            try {
                int typeObs = Character.getNumericValue(typeString.charAt(4));
                this.createInitArea(new Key(GameConfig.AreaType.OBSTACLE, typeObs), new Point(obs.get(i).getPosX(), obs.get(i).getPosY()));
            } catch (Exception e){
                Debug.warn("UserData - constructor: get obstacle type fail!!! ");
                e.printStackTrace();
            }
        }
        Debug.info("size : " + mapIdToArea.size());
    }

    public void updateAfterGetModel(){
        this.mapIdToArea = new HashMap<Integer, Area>();            // 1
        this.mapTypeToIds = new HashMap<Key,HashSet<Integer>>();  // 3
        this.builderWorkingAreas = new HashSet<Area>();             // 4
        // update all building, obstacles, military each time user login
        String jsonString = null;
        // restore 1
        for(HashMap.Entry<Integer,Integer> entry : this.dataModel.mapIdToClassType.entrySet()){
            int id = entry.getKey();
            int type = entry.getValue();
            jsonString = this.dataModel.mapIdToJsonString.get(id);
            switch (type){
                case GameConfig.AreaType.ARMY_CAMP:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, ArmyCamp.class));
                    break;
                case GameConfig.AreaType.BARRACK:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, Barrack.class));
                    break;
                case GameConfig.AreaType.BUILDER_HUT:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, BuilderHut.class));
                    break;
                case GameConfig.AreaType.DEFENSE:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, DefenseBuilding.class));
                    break;
                case GameConfig.AreaType.RESOURCE:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, Resource.class));
                    break;
                case GameConfig.AreaType.STORAGE:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, Storage.class));
                    break;
                case GameConfig.AreaType.TOWN_HALL:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, TownHall.class));
                    break;
                case GameConfig.AreaType.CLAN_CASTLE:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, ClanCastle.class));
                    break;
                case GameConfig.AreaType.LABORATORY:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, Laboratory.class));
                    break;
                case GameConfig.AreaType.OBSTACLE:
                    this.mapIdToArea.put(id, gson.fromJson(jsonString, Obstacle.class));
                    break;
                default:
                    break;
            }
        }
        this.userMap.loadEachLogin(this.mapIdToArea);               // restore 2
        // restore 3, 4
        for(HashMap.Entry<Integer, Area> entry : this.mapIdToArea.entrySet()){
            Area area = entry.getValue();
            boolean isBuilderWorkingOn = area.updateStatus();

            if(isBuilderWorkingOn){
                this.builderWorkingAreas.add(area);
            }
            // save neccessary map<type, ids>
            // all following buildings need to be gotten current number to check if user can add a new building
            this.updateMapTypeToIds(area);
        }
    }

    private void updateMapTypeToIds(Area area){
        int type = area.getType();
        switch (type){
            case GameConfig.AreaType.ARMY_CAMP:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.BARRACK:
                Barrack barrack = (Barrack) area;
                this.addIdToListId(new Key(type, barrack.getBarrackType()), area.getId());
                break;
            case GameConfig.AreaType.BUILDER_HUT:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.DEFENSE:
                DefenseBuilding defenseBuilding = (DefenseBuilding) area;
                this.addIdToListId(new Key(type, defenseBuilding.getDefenseType()), area.getId());
                break;
            case GameConfig.AreaType.RESOURCE:
                Resource resource = (Resource) area;
                this.addIdToListId(new Key(type, resource.getResourceType()), area.getId());
                break;
            case GameConfig.AreaType.STORAGE:
                Storage storage = (Storage) area;
                this.addIdToListId(new Key(type, storage.getStorageType()), area.getId());
                break;
            case GameConfig.AreaType.TOWN_HALL:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.CLAN_CASTLE:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.LABORATORY:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.OBSTACLE:
                this.addIdToListId(new Key(type, 0), area.getId());
                break;
            default:

        }
    }

    private void addIdToListId(Key pairType, int id){
        if(!this.mapTypeToIds.containsKey(pairType)){
            this.mapTypeToIds.put(pairType, new HashSet<Integer>());
        }
        this.mapTypeToIds.get(pairType).add(id);
    }

    private void removeIdToListId(Key pairType, int id){
        if(!this.mapTypeToIds.containsKey(pairType)){
            this.mapTypeToIds.put(pairType, new HashSet<Integer>());
        }
        if(this.mapTypeToIds.get(pairType).contains(id)){
            this.mapTypeToIds.get(pairType).remove(id);
        }
    }

    public void updateBuilderWorkingAreas(){
        // update builderWorkingAreas (hashset) each time user send a request to server
        for (Area area : this.builderWorkingAreas) {
            boolean isBuilderWorkingOn = area.updateStatus();
            if(! isBuilderWorkingOn){
                // a new builder is released
                this.builderWorkingAreas.remove(area);
                if(area.getType() == GameConfig.AreaType.OBSTACLE){
                    this.removeArea(area.getId());
                }
            }
        }
    }

    void updateMilitary(){
        // build 2 ...

    }

    public Area getAreaById(int id){
        return this.mapIdToArea.getOrDefault(id,null);
    }

    public HashSet<Integer> getObjectIdsByType(int typeLevel1, int typeLevel2){
//        if(!this.mapTypeToIds.containsKey(new Key(typeLevel1, typeLevel2))){
////            Debug.info("not contain key " + typeLevel1 + " " + typeLevel2);
//        }   else {
////            Debug.info("contain key " + typeLevel1 + " " + typeLevel2);
//        }
        return this.mapTypeToIds.getOrDefault(new Key(typeLevel1, typeLevel2), new HashSet<Integer>());
    }

    public int getResourceCapacity(int type){
        if(type <1 || type > 3){
            return 0;
        }
        int capacity = 0;
        HashSet<Integer> listIds = this.mapTypeToIds.get(new Key(GameConfig.AreaType.STORAGE, type));
        if(listIds!=null)
        for(Integer ids : listIds){
            Storage storage = (Storage) this.mapIdToArea.get(ids);
            capacity += storage.getCapacity();
        }
        TownHall th = (TownHall) mapIdToArea.get(mapTypeToIds.get(new Key(GameConfig.AreaType.TOWN_HALL,0)).iterator().next());
        capacity += th.getCapacity(type);
        return capacity;
    }

    // need to be synchronized when create, delete Area
    // do it later
    private void createInitArea(Key type, Point pos){
         createAndAddAreaWithCheck(type,pos,false);
    }

    public int createAndAddArea(Key type, Point pos){
        return createAndAddAreaWithCheck(type,pos,true);
    }

    private int createAndAddAreaWithCheck(Key type, Point pos, boolean init){
        // return objectId if ok, 0 otherwise
        int newId = this.dataModel.idCounter;
        this.dataModel.idCounter++;
        // create new Area
        Area area = null;

        switch (type.first){
            case GameConfig.AreaType.ARMY_CAMP:
                area = new ArmyCamp(newId);
                break;
            case GameConfig.AreaType.BARRACK:
                area = new Barrack(newId,type.second);
                break;
            case GameConfig.AreaType.BUILDER_HUT:
                area = new BuilderHut(newId, this.getObjectIdsByType(type.first, 0).size());
                break;
            case GameConfig.AreaType.DEFENSE:
                area = new DefenseBuilding(newId,type.second);
                break;
            case GameConfig.AreaType.RESOURCE:
                area = new Resource(newId,type.second);
                break;
            case GameConfig.AreaType.STORAGE:
                area = new Storage(newId,type.second);
                break;
            case GameConfig.AreaType.TOWN_HALL:
                area = new TownHall(newId);
                break;
            case GameConfig.AreaType.CLAN_CASTLE:
                area = new ClanCastle(newId);
                break;
            case GameConfig.AreaType.LABORATORY:
                area = new Laboratory(newId);
                break;
            case GameConfig.AreaType.OBSTACLE:
                area = new Obstacle(newId, type.second);
                break;
            default:
                return 0;
        }
        if(!init){
            if(area.getType() != GameConfig.AreaType.OBSTACLE){
                try {
                    Building currentArea = (Building) area;
                    currentArea.finishUpgrade();
                }   catch (Exception e){
                    e.printStackTrace();
                }

            }
            this.userMap.addObject(area,pos);
            this.addArea(newId, area);
            this.addIdToListId(type, newId);
            return newId;
        }

        int numberBuilderAvailable = getNumberWorkerAvailable();
        if(numberBuilderAvailable<0){
            Debug.warn("UserData - createInitArea : numberBuilderAvailable<0");
        }   else if(numberBuilderAvailable==0){
            // no builder available
            return 0;
        }
        // check valid position
        if(!this.userMap.checkIfFreeSpace(pos, area.getSize())){
            // invalid position
            return 0;
        }

        //check full building for this type
        if(this.getObjectIdsByType(type.first, type.second).size() >= this.getMaxNumberCanBuild(type)){
            // cannot add more for this type
            return 0;
        }

        // add Area to data
        this.userMap.addObject(area,pos);
        this.addArea(newId, area);
        this.addIdToListId(type, newId);
        boolean isBuilderWorking = area.updateStatus();
        if(isBuilderWorking){
            this.builderWorkingAreas.add(area);
        }
        return newId;
    }

    private void addArea(int id, Area area){
        this.mapIdToArea.put(id, area);
        int type = area.getType();
        this.dataModel.mapIdToClassType.put(id, type);
        this.dataModel.mapIdToJsonString.put(id, gson.toJson(area));
    }

    public void removeArea(int id){
        this.dataModel.mapIdToClassType.remove(id);
        this.dataModel.mapIdToJsonString.remove(id);
        Area area = this.mapIdToArea.get(id);
        this.mapIdToArea.remove(id);
        this.userMap.deleteObject(id);
        if(this.builderWorkingAreas.contains(area)){
            this.builderWorkingAreas.remove(area);
        }
        int type = area.getType();
        switch (type){
            case GameConfig.AreaType.ARMY_CAMP:
                this.removeIdToListId(new Key(type, 0),id );
                break;
            case GameConfig.AreaType.BARRACK:
                Barrack barrack = (Barrack) area;
                this.removeIdToListId(new Key(type, barrack.getBarrackType()), id );
                break;
            case GameConfig.AreaType.BUILDER_HUT:
                this.removeIdToListId(new Key(type, 0), area.getId());
                break;
            case GameConfig.AreaType.DEFENSE:
                DefenseBuilding defenseBuilding = (DefenseBuilding) area;
                this.removeIdToListId(new Key(type, defenseBuilding.getDefenseType()), id);
                break;
            case GameConfig.AreaType.RESOURCE:
                Resource resource = (Resource) area;
                this.removeIdToListId(new Key(type, resource.getResourceType()),id );
                break;
            case GameConfig.AreaType.STORAGE:
                Storage storage = (Storage) area;
                this.removeIdToListId(new Key(type, storage.getStorageType()), id );
                break;
            case GameConfig.AreaType.TOWN_HALL:
                this.removeIdToListId(new Key(type, 0), id );
                break;
            case GameConfig.AreaType.CLAN_CASTLE:
                this.removeIdToListId(new Key(type, 0), id );
                break;
            case GameConfig.AreaType.LABORATORY:
                this.removeIdToListId(new Key(type, 0), id );
                break;
            case GameConfig.AreaType.OBSTACLE:
                this.removeIdToListId(new Key(type, 0), id );
                break;
            default:
        }
    }

    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        currentByteBuffer.putInt(this.dataModel.id);
        currentByteBuffer.putInt(mapIdToArea.size());
        System.out.println("send to client number building + obstacle:" + mapIdToArea.size());
        for (HashMap.Entry<Integer, Area> entry : mapIdToArea.entrySet()){
            int objectId = entry.getKey();
            entry.getValue().packToByteBuffer(currentByteBuffer);
            currentByteBuffer.putInt(userMap.mapIdToPosition.get(objectId).x);
            currentByteBuffer.putInt(userMap.mapIdToPosition.get(objectId).y);
        }

    }
    public int getNumberWorkerAvailable(){
        // check worker available
        int builderHutType = GameConfig.AreaType.BUILDER_HUT;
        int n1 = this.getObjectIdsByType(builderHutType, 0).size();
        int n2 = this.builderWorkingAreas.size();
        return n1-n2;
    }
    public int getTownHallLevel(){
        try {
            TownHall th = (TownHall) mapIdToArea.get(mapTypeToIds.get(new Key(GameConfig.AreaType.TOWN_HALL,0)).iterator().next());
            return th.getCurrentLevel();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int getNumberByType(Key type){
        HashSet<Integer> ids = this.mapTypeToIds.get(type);
        if(ids == null){
            return 0;
        }
        return ids.size();
    }

    public int getMaxNumberCanBuild(Key type){
        int levelTownHall = this.getTownHallLevel();
        switch (type.first){
            case GameConfig.AreaType.ARMY_CAMP:
                return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getAMC1();
            case GameConfig.AreaType.BARRACK:
                switch (type.second){
                    case 0:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getBAR1();
                    case 1:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getBAR2();
                }
            case GameConfig.AreaType.BUILDER_HUT:
                return GameConfig.BUILDERHUT.getBDH1().size();
            case GameConfig.AreaType.DEFENSE:
                switch (type.second){
                    case 1:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF1();
                    case 2:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF2();
                    case 3:// case WALL ..., defenseType = 3
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getWAL1();
//                    case 4:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF4();
//                    case 5:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF5();
//                    case 6:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF6();
//                    case 7:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF7();
//                    case 8:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF8();
//                    case 9:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF9();
//                    case 10:// no DEF10
//                        return 1;
//                    case 11:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF11();
//                    case 12:
//                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF12();
                }

            case GameConfig.AreaType.RESOURCE:
                switch (type.second){
                    case 1:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getRES1();
                    case 2:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getRES2();
                    case 3:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getRES3();
                }

            case GameConfig.AreaType.STORAGE:
                switch (type.second){
                    case 1:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getSTO1();
                    case 2:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getSTO2();
                    case 3:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getSTO3();
                }
            case GameConfig.AreaType.CLAN_CASTLE:
                return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getCLC1();
            case GameConfig.AreaType.LABORATORY:
                return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getLAB1();
            default:
        }
        // default is 1 building : type = townhall
        return 1;
    }

    public void addAreaBuilderWorking(Area area){
        this.builderWorkingAreas.add(area);
    }
    public void showInfo(){
        this.userMap.showMap();
        System.out.println("number Builder available :" + this.getNumberWorkerAvailable());
        for(HashMap.Entry<Integer, Area> entry : mapIdToArea.entrySet()){
            Area area = entry.getValue();
            Point pos = this.userMap.mapIdToPosition.get(area.getId());
            Key size = area.getSize();
            System.out.println("At (" + (int) (pos.getX()) + "," + (int) pos.getY() + ")->(" + (int) (pos.getX()+size.first) + "," + (int) (pos.getY()+size.second) + ") has " + area.toString());
        }
    }

}
