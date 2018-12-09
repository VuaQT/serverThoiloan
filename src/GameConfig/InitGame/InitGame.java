package GameConfig.InitGame;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitGame {

    @SerializedName("map")
    @Expose
    private Map map;
    @SerializedName("player")
    @Expose
    private Player player;
    @SerializedName("obs")
    @Expose
    private List<Ob> obs = null;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Ob> getObs() {
        return obs;
    }

    public void setObs(List<Ob> obs) {
        this.obs = obs;
    }

}