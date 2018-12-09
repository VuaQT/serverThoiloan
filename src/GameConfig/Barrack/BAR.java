
package GameConfig.Barrack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BAR {

    @SerializedName("darkElixir")
    @Expose
    private Integer darkElixir;
    @SerializedName("elixir")
    @Expose
    private Integer elixir;
    @SerializedName("buildTime")
    @Expose
    private Integer buildTime;
    @SerializedName("hitpoints")
    @Expose
    private Integer hitpoints;
    @SerializedName("queueLength")
    @Expose
    private Integer queueLength;
    @SerializedName("townHallLevelRequired")
    @Expose
    private Integer townHallLevelRequired;
    @SerializedName("unlockedUnit")
    @Expose
    private String unlockedUnit;
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

    public Integer getElixir() {
        return elixir;
    }

    public void setElixir(Integer elixir) {
        this.elixir = elixir;
    }

    public Integer getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Integer buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Integer getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(Integer queueLength) {
        this.queueLength = queueLength;
    }

    public Integer getTownHallLevelRequired() {
        return townHallLevelRequired;
    }

    public void setTownHallLevelRequired(Integer townHallLevelRequired) {
        this.townHallLevelRequired = townHallLevelRequired;
    }

    public String getUnlockedUnit() {
        return unlockedUnit;
    }

    public void setUnlockedUnit(String unlockedUnit) {
        this.unlockedUnit = unlockedUnit;
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