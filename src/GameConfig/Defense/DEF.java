package GameConfig.Defense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DEF {

    @SerializedName("damagePerSecond")
    @Expose
    private Integer damagePerSecond;
    @SerializedName("damagePerShot")
    @Expose
    private Integer damagePerShot;
    @SerializedName("hitpoints")
    @Expose
    private Integer hitpoints;
    @SerializedName("costToLoad")
    @Expose
    private Integer costToLoad;
    @SerializedName("darkElixir")
    @Expose
    private Integer darkElixir;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("buildTime")
    @Expose
    private Integer buildTime;
    @SerializedName("townHallLevelRequired")
    @Expose
    private Integer townHallLevelRequired;
    @SerializedName("unlockSkill")
    @Expose
    private Integer unlockSkill;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getDamagePerSecond() {
        return damagePerSecond;
    }

    public void setDamagePerSecond(Integer damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public Integer getDamagePerShot() {
        return damagePerShot;
    }

    public void setDamagePerShot(Integer damagePerShot) {
        this.damagePerShot = damagePerShot;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public Integer getCostToLoad() {
        return costToLoad;
    }

    public void setCostToLoad(Integer costToLoad) {
        this.costToLoad = costToLoad;
    }

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

    public Integer getUnlockSkill() {
        return unlockSkill;
    }

    public void setUnlockSkill(Integer unlockSkill) {
        this.unlockSkill = unlockSkill;
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