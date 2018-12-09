
package GameConfig.ClanCastle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CLC1 {

    @SerializedName("darkElixir")
    @Expose
    private Integer darkElixir;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("hitpoints")
    @Expose
    private Integer hitpoints;
    @SerializedName("troopCapacity")
    @Expose
    private Integer troopCapacity;
    @SerializedName("buildTime")
    @Expose
    private Integer buildTime;
    @SerializedName("townHallLevelRequired")
    @Expose
    private Integer townHallLevelRequired;
    @SerializedName("range")
    @Expose
    private Integer range;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getDarkElixir() {
        return darkElixir;
    }

    public void setDarkElixir(Integer darkElixir) {
        this.darkElixir = darkElixir;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Integer getTroopCapacity() {
        return troopCapacity;
    }

    public void setTroopCapacity(Integer troopCapacity) {
        this.troopCapacity = troopCapacity;
    }

    public Integer getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Integer buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getTownHallLevelRequired() {
        return townHallLevelRequired;
    }

    public void setTownHallLevelRequired(Integer townHallLevelRequired) {
        this.townHallLevelRequired = townHallLevelRequired;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}