package cmd.send;

import bitzero.server.extensions.data.BaseMsg;
import cmd.CmdDefine;
import model.UserData;
import model.UserResources;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/14/2018.
 */


public class ResponseGetUserResources extends BaseMsg {
    ByteBuffer byteBuffer;
    public ResponseGetUserResources(UserResources userResources) {
        super(CmdDefine.GET_USER_RESOURCES);
        this.byteBuffer = makeBuffer();
        userResources.packToByteBuffer(this.byteBuffer);
    }

    @Override
    public byte[] createData() {
        return packBuffer(this.byteBuffer);
    }

}
