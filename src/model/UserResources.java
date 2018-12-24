package model;

import GameConfig.GameConfig;
import GameConfig.Resource.RE;
import util.ResourceType;
import util.database.DataModel;

import java.nio.ByteBuffer;

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

    public void decreaseCoin(int amount){
        this.coin -= amount;
    }
    public void decreaseResource(ResourceType resourceType){
        this.gold -= resourceType.gold;
        this.elixir -= resourceType.elixir;
        this.darkElixir -= resourceType.darkElixir;
    }
    public void inreaseResource(ResourceType resourceType, float ratio){
        this.gold += resourceType.gold * ratio;
        this.elixir += resourceType.elixir * ratio;
        this.darkElixir += resourceType.darkElixir * ratio;
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

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        currentByteBuffer.putInt(id);
//        currentByteBuffer.put ... username;
//        currentByteBuffer.putInt(username.length());
//        for(int i=0;i<username.length();i++){
//            currentByteBuffer.putChar(username.charAt(i));
//        }
        currentByteBuffer.putInt(levelPoint);
        currentByteBuffer.putInt(exp);
        currentByteBuffer.putInt(trophy);
        currentByteBuffer.putInt(gold);
        currentByteBuffer.putInt(elixir);
        currentByteBuffer.putInt(darkElixir);
        currentByteBuffer.putInt(shieldTime);
        currentByteBuffer.putInt(coin);
    }
}
