package GameConfig.Resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RE {

    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("elixir")
    @Expose
    private Integer elixir;
    @SerializedName("darkElixir")
    @Expose
    private Integer darkElixir;
    @SerializedName("buildTime")
    @Expose
    private Integer buildTime;
    @SerializedName("gem")
    @Expose
    private Integer gem;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("productivity")
    @Expose
    private Integer productivity;
    @SerializedName("hitpoints")
    @Expose
    private Integer hitpoints;
    @SerializedName("townHallLevelRequired")
    @Expose
    private Integer townHallLevelRequired;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getElixir() {
        return elixir;
    }

    public void setElixir(Integer elixir) {
        this.elixir = elixir;
    }

    public Integer getDarkElixir() {
        return darkElixir;
    }

    public void setDarkElixir(Integer darkElixir) {
        this.darkElixir = darkElixir;
    }

    public Integer getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Integer buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getGem() {
        return gem;
    }

    public void setGem(Integer gem) {
        this.gem = gem;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProductivity() {
        return productivity;
    }

    public void setProductivity(Integer productivity) {
        this.productivity = productivity;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Integer getTownHallLevelRequired() {
        return townHallLevelRequired;
    }

    public void setTownHallLevelRequired(Integer townHallLevelRequired) {
        this.townHallLevelRequired = townHallLevelRequired;
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