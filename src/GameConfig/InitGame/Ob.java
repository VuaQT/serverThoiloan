package GameConfig.InitGame;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ob {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("posX")
    @Expose
    private Integer posX;
    @SerializedName("posY")
    @Expose
    private Integer posY;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

}