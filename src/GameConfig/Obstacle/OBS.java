package GameConfig.Obstacle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OBS {

    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("buildTime")
    @Expose
    private Integer buildTime;
    @SerializedName("elixir")
    @Expose
    private Integer elixir;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("rewardElixir")
    @Expose
    private Integer rewardElixir;
    @SerializedName("rewardDarkElixir")
    @Expose
    private Integer rewardDarkElixir;
    @SerializedName("respawnRate")
    @Expose
    private Integer respawnRate;
    @SerializedName("exp")
    @Expose
    private Integer exp;

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

    public Integer getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Integer buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getElixir() {
        return elixir;
    }

    public void setElixir(Integer elixir) {
        this.elixir = elixir;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getRewardElixir() {
        return rewardElixir;
    }

    public void setRewardElixir(Integer rewardElixir) {
        this.rewardElixir = rewardElixir;
    }

    public Integer getRewardDarkElixir() {
        return rewardDarkElixir;
    }

    public void setRewardDarkElixir(Integer rewardDarkElixir) {
        this.rewardDarkElixir = rewardDarkElixir;
    }

    public Integer getRespawnRate() {
        return respawnRate;
    }

    public void setRespawnRate(Integer respawnRate) {
        this.respawnRate = respawnRate;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

}