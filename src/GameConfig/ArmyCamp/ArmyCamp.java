
package GameConfig.ArmyCamp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArmyCamp {

    @SerializedName("AMC_1")
    @Expose
    private List<AMC1> aMC1 = null;

    public List<AMC1> getAMC1() {
        return aMC1;
    }

    public void setAMC1(List<AMC1> aMC1) {
        this.aMC1 = aMC1;
    }

}