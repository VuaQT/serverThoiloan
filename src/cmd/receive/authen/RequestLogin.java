package cmd.receive.authen;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;

import java.nio.ByteBuffer;

public class RequestLogin extends BaseCmd {
    public int uid; // each user is identified by their uid;
    public String username = "";
    public RequestLogin(DataCmd dataCmd) {
        super(dataCmd);
    }
    
    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        try {
            uid = readInt(bf);
            username = readString(bf);
        } catch (Exception e) {
            
        }
    }
    
}
