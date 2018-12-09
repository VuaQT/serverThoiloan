package GameConfig.InitGame;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("elixir")
    @Expose
    private Integer elixir;
    @SerializedName("darkElixir")
    @Expose
    private Integer darkElixir;
    @SerializedName("builderNumber")
    @Expose
    private Integer builderNumber;

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getElixir() {
        return elixir;
    }

    public void setElixir(Integer elixir) {
        this.elixir = elixir;
    }

    public Integer getDarkElixir() {
        return darkElixir;
    }

    public void setDarkElixir(Integer darkElixir) {
        this.darkElixir = darkElixir;
    }

    public Integer getBuilderNumber() {
        return builderNumber;
    }

    public void setBuilderNumber(Integer builderNumber) {
        this.builderNumber = builderNumber;
    }

}