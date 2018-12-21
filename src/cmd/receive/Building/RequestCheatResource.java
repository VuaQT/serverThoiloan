package cmd.receive.Building;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;
import bitzero.util.common.business.CommonHandle;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class RequestCheatResource extends BaseCmd {
    public int type;
    public int amount;
    public RequestCheatResource(DataCmd dataCmd) {
        super(dataCmd);
        unpackData();
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        try {
            type = readInt(bf);
            amount = readInt(bf);
        } catch (Exception e) {
            CommonHandle.writeErrLog(e);
        }
    }
}
