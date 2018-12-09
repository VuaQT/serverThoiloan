package GameConfig.Storage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Storage {

    @SerializedName("STO")
    @Expose
    private List<List<STO>> sTO = null;

    public List<List<STO>> getSTO() {
        return sTO;
    }

    public void setSTO(List<List<STO>> sTO) {
        this.sTO = sTO;
    }

}