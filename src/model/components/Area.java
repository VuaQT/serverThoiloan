package model.components;

import util.Key;

import java.nio.ByteBuffer;

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
    public boolean updateStatus(){
        // return true if a builder is working on
        return false;
    }

    public Key getSize(){
        return new Key(0,0);
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

    public void packToByteBuffer(ByteBuffer currentByteBuffer){
        currentByteBuffer.putInt(id);
        currentByteBuffer.putInt(type);
    }
}

