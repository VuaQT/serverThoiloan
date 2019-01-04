package cmd.receive.Building;

import GameConfig.GameConfig;
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
public class RequestAddBuilding extends BaseCmd {
    public Key type;
    public Point pos;
    public RequestAddBuilding(DataCmd dataCmd) {
        super(dataCmd);
        unpackData();
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        int p1,p2;
        try {
            p1 = readInt(bf);
            p2 = readInt(bf);
            pos = new Point(p1,p2);

            p1 = readInt(bf);
            if(!GameConfig.checkHaveType2(p1)){
                p2 = 0;
            }   else {
                p2 = readInt(bf);
            }
            type = new Key(p1,p2);

        } catch (Exception e) {
            CommonHandle.writeErrLog(e);
        }
    }


}