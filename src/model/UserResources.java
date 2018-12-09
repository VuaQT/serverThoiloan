package model;

import GameConfig.GameConfig;
import util.database.DataModel;

public class UserResources extends DataModel{
    private int id;
    private String username;
    private int levelPoint;
    private int exp;
    private int trophy;
    private int gold;
    private int elixir;
    private int darkElixir;
    private int shieldTime;
    private int coin;

    public UserResources(int id, String username) {
        super();
        this.id = id;
        this.username = username;
        this.gold = GameConfig.INIT_GAME.getPlayer().getGold();
        this.coin = GameConfig.INIT_GAME.getPlayer().getCoin();
        this.elixir = GameConfig.INIT_GAME.getPlayer().getElixir();
        this.darkElixir = GameConfig.INIT_GAME.getPlayer().getDarkElixir();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setLevelPoint(int levelPoint) {
        this.levelPoint = levelPoint;
    }

    public int getLevelPoint() {
        return levelPoint;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public void setTrophy(int trophy) {
        this.trophy = trophy;
    }

    public int getTrophy() {
        return trophy;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public int getElixir() {
        return elixir;
    }

    public void setDarkElixir(int darkElixir) {
        this.darkElixir = darkElixir;
    }

    public int getDarkElixir() {
        return darkElixir;
    }

    public void setShieldTime(int shieldTime) {
        this.shieldTime = shieldTime;
    }

    public int getShieldTime() {
        return shieldTime;
    }

    public void setGCoin(int gCoin) {
        this.coin = gCoin;
    }

    public int getGCoin() {
        return coin;
    }

}
