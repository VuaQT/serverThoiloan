package GameConfig.Laboratory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laboratory {

    @SerializedName("LAB_1")
    @Expose
    private List<LAB1> lAB1 = null;

    public List<LAB1> getLAB1() {
        return lAB1;
    }

    public void setLAB1(List<LAB1> lAB1) {
        this.lAB1 = lAB1;
    }

}