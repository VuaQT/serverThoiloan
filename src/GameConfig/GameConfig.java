package GameConfig;

import GameConfig.ArmyCamp.ArmyCamp;
import GameConfig.Barrack.Barrack;
import GameConfig.BuilderHut.BuilderHut;
import GameConfig.ClanCastle.ClanCastle;
import GameConfig.Defense.Defense;
import GameConfig.InitGame.InitGame;
import GameConfig.Laboratory.Laboratory;
import GameConfig.Obstacle.Obstacle;
import GameConfig.Resource.Resource;
import GameConfig.Storage.Storage;
import GameConfig.TownHall.TownHall;
import GameConfig.Wall.Wall;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class GameConfig {
    public static int boardWidth;
    public static int boardHeight;
    public static float StopUpgradeHarvestRatio = 0.5f;
    public interface ResourceType{
        public int GOLD = 1;
        public int ELIXIR = 2;
        public int DARK_ELIXIR = 3;
    }
    public interface AreaType{
        public int ARMY_CAMP = 1;
        public int BARRACK = 2; // type2
        public int BUILDER_HUT = 3;
        public int CLAN_CASTLE = 4;
        public int DEFENSE = 5; // type2 : add a defenseType to DefenseBuilding class
        // WALL is a type of DEFENSE, need to add Wall.json to Defence.json
        public int LABORATORY = 6;
        public int OBSTACLE = 7; // type2 : add an obstacleType ..
        public int RESOURCE = 8; //type2 :  add a resourceType
        public int STORAGE = 9; //type2 :  add a storageType
        public int TOWN_HALL = 10;
    }
    public interface AddBuildingStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_ENOUGH_RESOURCES = 1;
        public int FAIL_NO_WORKER_AVAILABLE = 2;
        public int FAIL_NOT_VALID_POSITION = 3;
        public int FAIL_ENOUGH_FOR_THIS_LEVEL = 4;
        public int FAIL_NOT_VALID_TYPE = 5;
        public int FAIL_UNKNOWN = 6;
    }

    public interface MoveBuildingStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_VALID_ID = 1;
        public int FAIL_NOT_VALID_POSITION = 2;
        public int FAIL_UNKNOWN = 4;
    }

    public interface StopUpgradingStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_VALID_ID = 1;
        public int FAIL_NOT_UPGRADING = 2;
        public int FAIL_UNKNOWN = 3;
    }

    public interface UpgradeStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_VALID_ID = 1;
        public int UPGRADING_ALREADY = 2;
        public int MAX_UPGRADE_LEVEL = 3;
        public int FAIL_NOT_ENOUGH_RESOURCES = 4;
        public int FAIL_NO_WORKER_AVAILABLE = 5;
        public int FAIL_NOT_ENOUGH_LEVEL_TOWNHALL = 6;
        public int FAIL_UNKNOWN = 7;
    }

    public interface UpgradeNowStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_VALID_ID = 1;
        public int FAIL_NOT_UPGRADING = 4;
        public int FAIL_UNKNOWN = 3;
    }

    public interface HarvestStatus {
        public int SUCCESS = 0;
        public int FAIL_NOT_VALID_ID = 1;
        public int FAIL_UPGRADING = 4; // upgrading khong sinh ra tai nguyen
        public int FAIL_UNKNOWN = 3;
    }


    public static ArmyCamp ARMYCAMP;
    public static Barrack BARRACK;
    public static BuilderHut BUILDERHUT;
    public static ClanCastle CLANCASTLE;
    public static Defense DEFENSE;
    public static InitGame INIT_GAME;
    public static Laboratory LABORATORY;
    public static Obstacle OBSTACLE;
    public static Resource RESOURCE;
    public static TownHall TOWN_HALL;
    public static Wall WALL;
    public static Storage STORAGE;
    public static void loadData(){
        // read json files
        Gson gson = new Gson();
        String folderName = "res\\Config json";
        try {
            boardWidth = 42;
            boardHeight = 42;
            ARMYCAMP = gson. fromJson(new FileReader(folderName + "\\ArmyCamp.json.txt"),ArmyCamp.class);
            BARRACK =  gson. fromJson(new FileReader(folderName + "\\Barrack.json.txt"),Barrack.class);
            BUILDERHUT = gson. fromJson(new FileReader(folderName + "\\BuilderHut.json.txt"),BuilderHut.class);
            CLANCASTLE = gson. fromJson(new FileReader(folderName + "\\ClanCastle.json.txt"),ClanCastle.class);
            DEFENSE = gson. fromJson(new FileReader(folderName + "\\Defence.json.txt"),Defense.class);
            INIT_GAME = gson. fromJson(new FileReader(folderName + "\\InitGame.json.txt"),InitGame.class);
            LABORATORY = gson. fromJson(new FileReader(folderName + "\\Laboratory.json.txt"),Laboratory.class);
            OBSTACLE = gson. fromJson(new FileReader(folderName + "\\Obstacle.json.txt"),Obstacle.class);
            RESOURCE = gson. fromJson(new FileReader(folderName + "\\Resource.json.txt"),Resource.class);
            TOWN_HALL = gson. fromJson(new FileReader(folderName + "\\TownHall.json.txt"),TownHall.class);
            WALL = gson. fromJson(new FileReader(folderName + "\\Wall.json.txt"),Wall.class);
            STORAGE = gson. fromJson(new FileReader(folderName + "\\Storage.json.txt"),Storage.class);
            System.out.println("test GameConfig : " + INIT_GAME.getMap().getAMC1().getPosX());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkHaveType2(int type1){
        switch (type1){
            case AreaType.BARRACK:
                return true;
            case AreaType.OBSTACLE:
                return true;
            case AreaType.RESOURCE:
                return true;
            case AreaType.STORAGE:
                return true;
            case AreaType.DEFENSE:
                return true;
            default:
                return false;
        }
    }
}

