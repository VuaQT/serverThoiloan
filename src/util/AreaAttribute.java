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
                return new ResourceType(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
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

}
