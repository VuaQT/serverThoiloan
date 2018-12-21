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
    public int coin;

    public ResourceType() {
        this.gold = this.elixir = this.darkElixir = this.coin = 0;
    }

    public ResourceType(int gold, int elixir, int darkElixir, int coin) {
        this.gold = gold;
        this.elixir = elixir;
        this.darkElixir = darkElixir;
        this.coin = coin;
    }

    public boolean checkEnough(UserResources userResources){
        if(userResources.getCoin()< this.gold) return false;
        if(userResources.getElixir()< this.elixir) return false;
        if(userResources.getDarkElixir()< this.darkElixir) return false;
        if(userResources.getCoin()< this.coin) return false;
        return true;
    }
}
