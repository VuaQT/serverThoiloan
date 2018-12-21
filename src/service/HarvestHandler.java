package service;

/**
 * Created by Trungnq4 on 12/17/2018.
 */

import cmd.receive.Building.RequestCheatResource;
import cmd.send.ResponseStatus;
import model.UserResources;
import util.server.ServerConstant;

import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import cmd.CmdDefine;
import cmd.receive.Building.RequestBuildingId;
import event.eventType.DemoEventType;
import extension.FresherExtension;
import model.UserData;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class HarvestHandler  extends BaseClientRequestHandler {
    public static short HARVEST_MULTI_IDS = 5000;
    private final Logger logger = LoggerFactory.getLogger("UserHandler");

    public HarvestHandler () {
        super();
    }

    public void init() {
        /**
         *  register new event, so the core will dispatch event type to this class
         */
        getExtension().addEventListener(DemoEventType.CHANGE_NAME, this);
    }

    private FresherExtension getExtension() {
        return (FresherExtension) getParentExtension();
    }

    public void handleServerEvent(IBZEvent ibzevent) {

//        if (ibzevent.getType() == BZEventType.USER_DISCONNECT)
//            this.userDisconnect((User) ibzevent.getParameter(BZEventParam.USER));
//        else if (ibzevent.getType() == DemoEventType.CHANGE_NAME)
//            this.userChangeName((User) ibzevent.getParameter(DemoEventParam.USER), (String)ibzevent.getParameter(DemoEventParam.NAME));
    }

    public void handleClientRequest(User user, DataCmd dataCmd) {
        try {
            switch (dataCmd.getId()) {
                case CmdDefine.HARVEST:
                    RequestCheatResource buildingIdHarvest = new RequestCheatResource(dataCmd);
                    harvest(user, buildingIdHarvest.type, buildingIdHarvest.amount);
                    break;
            }
        } catch (Exception e) {
            logger.warn("USERHANDLER EXCEPTION " + e.getMessage());
            logger.warn(ExceptionUtils.getStackTrace(e));

        }

    }

    private void harvest(User user, int type, int amount) {
        try {
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userResources == null) {

            }
            // cheat
            int amountCheat = 1000;
            if(amount>0){
                amountCheat = amount;
            }
            switch (type){
                case 1: userResources.setCoin(userResources.getCoin()+amountCheat);
                    break;
                case 2: userResources.setElixir(userResources.getElixir() + amountCheat);
                    break;
                case 3: userResources.setDarkElixir(userResources.getDarkElixir() + amountCheat);
                    break;
                case 4: userResources.setCoin(userResources.getCoin() + amountCheat);
                    break;
            }
            send(new ResponseStatus(CmdDefine.HARVEST, amountCheat), user);

        } catch (Exception e) {

        }

    }
}
