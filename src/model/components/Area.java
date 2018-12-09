package model.components;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class Area {
    protected int id;
    protected int type;
    public Area(int id, int type) {
        super();
        this.id = id;
        this.type = type;
    }
    public void updateStatus(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

