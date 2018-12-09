package GameConfig.Wall;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wall {

    @SerializedName("WAL_1")
    @Expose
    private List<WAL1> wAL1 = null;

    public List<WAL1> getWAL1() {
        return wAL1;
    }

    public void setWAL1(List<WAL1> wAL1) {
        this.wAL1 = wAL1;
    }

}