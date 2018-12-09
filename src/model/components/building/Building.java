package model.components.building;

import model.components.Area;
import util.TimeManager;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Building extends Area {
    protected int currentLevel;
    protected int levelUpgrading; // (0: not building, not upgrading), (1:constructing), (>1:upgrading)
    protected int upgradedMoment; // upgradedMoment of latest Building or Upgrading

    public Building(int id, int type) {
        super(id, type);
        this.currentLevel = 1;
        this.levelUpgrading = 1;
        this.upgradedMoment = TimeManager.getTime();

    }

    public void updateStatus(){
        super.updateStatus();
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getUpgradedMoment() {
        return upgradedMoment;
    }

    public void setUpgradedMoment(int upgradedMoment) {
        this.upgradedMoment = upgradedMoment;
    }

    public void setLevelUpgrading(int levelUpgrading) {
        this.levelUpgrading = levelUpgrading;
    }

    public int getLevelUpgrading() {
        return levelUpgrading;
    }


}
