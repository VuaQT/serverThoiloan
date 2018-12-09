package GameConfig.TownHall;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TownHall {

    @SerializedName("TOW_1")
    @Expose
    private List<TOW1> tOW1 = null;

    public List<TOW1> getTOW1() {
        return tOW1;
    }

    public void setTOW1(List<TOW1> tOW1) {
        this.tOW1 = tOW1;
    }

}