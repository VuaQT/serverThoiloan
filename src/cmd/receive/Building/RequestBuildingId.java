package cmd.receive.Building;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;
import bitzero.util.common.business.CommonHandle;

import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class RequestBuildingId extends BaseCmd {
    public int id;
    public RequestBuildingId(DataCmd dataCmd) {
        super(dataCmd);
        unpackData();
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        try {
            id = readInt(bf);
        } catch (Exception e) {
            CommonHandle.writeErrLog(e);
        }
    }
}
