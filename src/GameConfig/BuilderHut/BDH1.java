package GameConfig.BuilderHut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BDH1 {

    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("hitpoints")
    @Expose
    private Integer hitpoints;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
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