
package GameConfig.Barrack;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Barrack {

    @SerializedName("BAR")
    @Expose
    private List<List<BAR>> bAR = null;

    public List<List<BAR>> getBAR() {
        return bAR;
    }

    public void setBAR(List<List<BAR>> bAR) {
        this.bAR = bAR;
    }

}