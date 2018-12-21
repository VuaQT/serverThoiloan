package cmd.receive.Building;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;
import bitzero.util.common.business.CommonHandle;
import cmd.obj.demo.DemoDirection;
import util.Key;

import java.awt.*;
import java.nio.ByteBuffer;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class RequestMoveBuilding extends BaseCmd {
    public int id;
    public Point pos = new Point(0,0);
    public RequestMoveBuilding(DataCmd dataCmd) {
        super(dataCmd);
        unpackData();
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        int p1,p2;
        try {
            id = readInt(bf);
            p1 = readInt(bf);
            p2 = readInt(bf);
            pos = new Point(p1,p2);
        } catch (Exception e) {
            CommonHandle.writeErrLog(e);
        }
    }


}