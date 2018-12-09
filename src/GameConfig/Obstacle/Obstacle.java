package GameConfig.Obstacle;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Obstacle {

    @SerializedName("OBS_")
    @Expose
    private List<OBS> oBS = null;

    public List<OBS> getOBS() {
        return oBS;
    }

    public void setOBS(List<OBS> oBS) {
        this.oBS = oBS;
    }

}