package GameConfig.Defense;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Defense {

    @SerializedName("DEF")
    @Expose
    private List<List<DEF>> dEF = null;

    public List<List<DEF>> getDEF() {
        return dEF;
    }

    public void setDEF(List<List<DEF>> dEF) {
        this.dEF = dEF;
    }

}