package cmd.send;

import bitzero.server.extensions.data.BaseMsg;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class ResponseStatus2 extends BaseMsg {
    int status;
    int num;
    public ResponseStatus2(short packetCode, int status, int num) {
        super(packetCode);
        this.status =status;
        this.num = num;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();
        bf.putInt(status);
        bf.putInt(num);
        return packBuffer(bf);
    }
}
