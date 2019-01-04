package model.components.building;

import GameConfig.GameConfig;
import util.Key;

import java.nio.ByteBuffer;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class ClanCastle extends Building {
    public ClanCastle(int id) {
        super(id, GameConfig.AreaType.CLAN_CASTLE);
    }
    public int getMaxLevel(){
        return GameConfig.CLANCASTLE.getCLC1().size();
    }
    public int getCurrentBuildTime(){
        return GameConfig.CLANCASTLE.getCLC1().get(this.upgradingLevel-1).getBuildTime();
    }
    public Key getSize(){
        return new Key(GameConfig.CLANCASTLE.getCLC1().get(this.currentLevel-1).getWidth(),GameConfig.CLANCASTLE.getCLC1().get(this.currentLevel-1).getHeight());
    }
    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        super.packToByteBuffer(currentByteBuffer);
    }
    public int getLevelTownHallRequiredToUpgrade(){
        return GameConfig.CLANCASTLE.getCLC1().get(this.currentLevel).getTownHallLevelRequired() ;
    }
    public String toString(){
        return "ClanCastle " + super.toString();
    }

}
