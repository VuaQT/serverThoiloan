package cmd.send;

import bitzero.server.extensions.data.BaseMsg;
import cmd.CmdDefine;
import model.UserData;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/14/2018.
 */


public class ResponseGetUserData extends BaseMsg {
    ByteBuffer byteBuffer;
    public ResponseGetUserData(UserData userData) {
        super(CmdDefine.GET_USER_DATA);
        this.byteBuffer = makeBuffer();
        userData.packToByteBuffer(this.byteBuffer);
    }

    @Override
    public byte[] createData() {
        return packBuffer(this.byteBuffer);
    }

}
