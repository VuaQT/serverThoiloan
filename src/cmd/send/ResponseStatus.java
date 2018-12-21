package cmd.send;

import bitzero.server.extensions.data.BaseMsg;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class ResponseStatus extends BaseMsg {
    int status;
    public ResponseStatus(short packetCode, int status) {
        super(packetCode);
        this.status =status;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();
        bf.putInt(status);
        return packBuffer(bf);
    }
}
