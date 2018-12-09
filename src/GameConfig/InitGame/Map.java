package GameConfig.InitGame;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("TOW_1")
    @Expose
    private POS tOW1;
    @SerializedName("AMC_1")
    @Expose
    private POS aMC1;
    @SerializedName("RES_1")
    @Expose
    private POS rES1;
    @SerializedName("BDH_1")
    @Expose
    private POS bDH1;
    @SerializedName("CLC_1")
    @Expose
    private POS cLC1;

    public POS getTOW1() {
        return tOW1;
    }

    public void setTOW1(POS tOW1) {
        this.tOW1 = tOW1;
    }

    public POS getAMC1() {
        return aMC1;
    }

    public void setAMC1(POS aMC1) {
        this.aMC1 = aMC1;
    }

    public POS getRES1() {
        return rES1;
    }

    public void setRES1(POS rES1) {
        this.rES1 = rES1;
    }

    public POS getBDH1() {
        return bDH1;
    }

    public void setBDH1(POS bDH1) {
        this.bDH1 = bDH1;
    }

    public POS getCLC1() {
        return cLC1;
    }

    public void setCLC1(POS cLC1) {
        this.cLC1 = cLC1;
    }

}