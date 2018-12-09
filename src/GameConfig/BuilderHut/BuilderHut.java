package GameConfig.BuilderHut;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuilderHut {

    @SerializedName("BDH_1")
    @Expose
    private List<BDH1> bDH1 = null;

    public List<BDH1> getBDH1() {
        return bDH1;
    }

    public void setBDH1(List<BDH1> bDH1) {
        this.bDH1 = bDH1;
    }

}