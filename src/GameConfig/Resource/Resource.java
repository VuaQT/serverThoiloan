package GameConfig.Resource;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {

    @SerializedName("RES")
    @Expose
    private List<List<RE>> rES = null;

    public List<List<RE>> getRES() {
        return rES;
    }

    public void setRES(List<List<RE>> rES) {
        this.rES = rES;
    }

}