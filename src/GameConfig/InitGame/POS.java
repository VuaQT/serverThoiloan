package GameConfig.InitGame;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POS {

    @SerializedName("posX")
    @Expose
    private Integer posX;
    @SerializedName("posY")
    @Expose
    private Integer posY;

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