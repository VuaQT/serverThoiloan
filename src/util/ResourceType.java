package util;

import GameConfig.GameConfig;
import model.UserData;
import model.UserResources;
import model.components.building.ResourcesBuildings.Resource;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class ResourceType {
    public int gold;
    public int elixir;
    public int darkElixir;

    public ResourceType() {
        this.gold = this.elixir = this.darkElixir = 0;
    }

    public ResourceType(int gold, int elixir, int darkElixir) {
        this.gold = gold;
        this.elixir = elixir;
        this.darkElixir = darkElixir;
    }

    public boolean checkEnough(UserResources userResources){
        if(userResources.getGold()< this.gold) return false;
        if(userResources.getElixir()< this.elixir) return false;
        if(userResources.getDarkElixir()< this.darkElixir) return false;
        return true;
    }
}
