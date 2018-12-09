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
    public static AreaType AREA_TYPE;
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
            boardWidth = 40;
            boardHeight = 40;
            AREA_TYPE = new AreaType();
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

}

