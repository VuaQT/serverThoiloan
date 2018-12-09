
package GameConfig.ClanCastle;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClanCastle {

    @SerializedName("CLC_1")
    @Expose
    private List<CLC1> cLC1 = null;

    public List<CLC1> getCLC1() {
        return cLC1;
    }

    public void setCLC1(List<CLC1> cLC1) {
        this.cLC1 = cLC1;
    }

}