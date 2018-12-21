package util;

import GameConfig.GameConfig;

import java.awt.*;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class AreaAttribute {
    public static ResourceType getResourceBuildNew(Key type){
        ResourceType resourceType = new ResourceType();
        switch (type.first){
            case GameConfig.AreaType.ARMY_CAMP:
                resourceType.elixir = GameConfig.ARMYCAMP.getAMC1().get(0).getElixir();
                resourceType.darkElixir = GameConfig.ARMYCAMP.getAMC1().get(0).getDarkElixir();
                break;
            case GameConfig.AreaType.BARRACK:
                resourceType.elixir = GameConfig.BARRACK.getBAR().get(type.second-1).get(0).getElixir();
                resourceType.darkElixir = GameConfig.BARRACK.getBAR().get(type.second-1).get(0).getDarkElixir();
                break;
            case GameConfig.AreaType.BUILDER_HUT:
                resourceType.coin = GameConfig.BUILDERHUT.getBDH1().get(0).getCoin();
                break;
            case GameConfig.AreaType.DEFENSE:
                resourceType.gold = GameConfig.DEFENSE.getDEF().get(type.second-1).get(0).getGold();
                resourceType.darkElixir = GameConfig.DEFENSE.getDEF().get(type.second-1).get(0).getDarkElixir();
                break;
            case GameConfig.AreaType.RESOURCE:
                resourceType.gold = GameConfig.RESOURCE.getRES().get(type.second-1).get(0).getGold();
                resourceType.elixir = GameConfig.RESOURCE.getRES().get(type.second-1).get(0).getElixir();
                resourceType.darkElixir = GameConfig.RESOURCE.getRES().get(type.second-1).get(0).getDarkElixir();
                break;
            case GameConfig.AreaType.STORAGE:
                resourceType.gold = GameConfig.STORAGE.getSTO().get(type.second-1).get(0).getGold();
                resourceType.elixir = GameConfig.STORAGE.getSTO().get(type.second-1).get(0).getElixir();
                resourceType.darkElixir = GameConfig.STORAGE.getSTO().get(type.second-1).get(0).getDarkElixir();
                break;
            case GameConfig.AreaType.LABORATORY:
                resourceType.elixir = GameConfig.LABORATORY.getLAB1().get(0).getElixir();
                resourceType.darkElixir = GameConfig.LABORATORY.getLAB1().get(0).getDarkElixir();
                break;
            default:
                return new ResourceType(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        return resourceType;
    }
    public static Key getSizeBuildNew(Key type){
        switch (type.first){
            case GameConfig.AreaType.ARMY_CAMP:
                return new Key(GameConfig.ARMYCAMP.getAMC1().get(0).getWidth(),GameConfig.ARMYCAMP.getAMC1().get(0).getHeight());
            case GameConfig.AreaType.BARRACK:
                return new Key(GameConfig.BARRACK.getBAR().get(type.second-1).get(0).getWidth(),GameConfig.BARRACK.getBAR().get(type.second-1).get(0).getHeight());
            case GameConfig.AreaType.BUILDER_HUT:
                return new Key(GameConfig.BUILDERHUT.getBDH1().get(0).getWidth(),GameConfig.BUILDERHUT.getBDH1().get(0).getHeight());
            case GameConfig.AreaType.DEFENSE:
                return new Key(GameConfig.DEFENSE.getDEF().get(type.second - 1).get(0).getWidth(),GameConfig.DEFENSE.getDEF().get(type.second - 1).get(0).getHeight());
            case GameConfig.AreaType.RESOURCE:
                return new Key(GameConfig.RESOURCE.getRES().get(type.second-1).get(0).getWidth(),GameConfig.RESOURCE.getRES().get(type.second-1).get(0).getHeight());
            case GameConfig.AreaType.STORAGE:
                return new Key(GameConfig.STORAGE.getSTO().get(type.second - 1).get(0).getWidth(),GameConfig.STORAGE.getSTO().get(type.second-1).get(0).getHeight());
            case GameConfig.AreaType.TOWN_HALL:
                return new Key(GameConfig.TOWN_HALL.getTOW1().get(0).getWidth(),GameConfig.TOWN_HALL.getTOW1().get(0).getHeight());
            case GameConfig.AreaType.CLAN_CASTLE:
                return new Key(GameConfig.CLANCASTLE.getCLC1().get(0).getWidth(),GameConfig.CLANCASTLE.getCLC1().get(0).getHeight());
            case GameConfig.AreaType.LABORATORY:
                return new Key(GameConfig.LABORATORY.getLAB1().get(0).getWidth(),GameConfig.LABORATORY.getLAB1().get(0).getHeight());
            default:
                return null;
        }
    }
    public static int getMaxNumberCanBuild(Key type, int levelTownHall){
        //TODO : get max number of building base on type and levelTownHall
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
                    case 3:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF3();
                    case 4:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF4();
                    case 5:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF5();
                    case 6:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF6();
                    case 7:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF7();
                    case 8:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF8();
                    case 9:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF9();
                    case 10:// no DEF10
                        return 1;
                    case 11:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF11();
                    case 12:
                        return GameConfig.TOWN_HALL.getTOW1().get(levelTownHall-1).getDEF12();
                    // TODO : case WALL ...
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
}
