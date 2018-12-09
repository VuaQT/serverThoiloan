package model.components.building.ResourcesBuildings;

import GameConfig.GameConfig;
import model.components.building.Building;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Storage extends Building{
    int storageType;
    public Storage(int id, int storageType) {
        super(id, GameConfig.AREA_TYPE.STORAGE);
        this.storageType = storageType;
    }

    public int getStorageType() {
        return storageType;
    }
}
