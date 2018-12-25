package service;

/**
 * Created by Trungnq4 on 12/17/2018.
 */

import GameConfig.GameConfig;
import cmd.receive.Building.RequestBuildingId;
import cmd.receive.Building.RequestCheatResource;
import cmd.send.ResponseStatus;
import cmd.send.ResponseStatus2;
import model.UserData;
import model.UserResources;
import model.components.building.ResourcesBuildings.Resource;
import util.server.ServerConstant;

import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import cmd.CmdDefine;
import event.eventType.DemoEventType;
import extension.FresherExtension;
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
                case CmdDefine.CHEAT_RESOURCE:
                    RequestCheatResource buildingIdHarvest = new RequestCheatResource(dataCmd);
                    cheat(user, buildingIdHarvest.type, buildingIdHarvest.amount);
                    break;
                case CmdDefine.HARVEST:
                    RequestBuildingId buildingId = new RequestBuildingId(dataCmd);
                    harvest(user,buildingId.id);
                    break;
            }
        } catch (Exception e) {
            logger.warn("USERHANDLER EXCEPTION " + e.getMessage());
            logger.warn(ExceptionUtils.getStackTrace(e));

        }

    }

    private void harvest(User user, int id){
        try {
            int amountHarvested = 0;
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            if (userResources == null || userData == null) {
                send(new ResponseStatus2(CmdDefine.HARVEST,GameConfig.HarvestStatus.FAIL_UNKNOWN, 0), user);
                return;
            }
            Resource resource = null;
            try{
                resource = (Resource) userData.getAreaById(id);
                if(resource == null){
                    send(new ResponseStatus2(CmdDefine.HARVEST, GameConfig.HarvestStatus.FAIL_NOT_VALID_ID,0), user);
                    return;
                }
            }   catch (Exception e){
                send(new ResponseStatus2(CmdDefine.HARVEST, GameConfig.HarvestStatus.FAIL_NOT_VALID_ID,0), user);
                return;
            }

            amountHarvested = resource.harvest();
            int typeHarvested = resource.getResourceType();
            int maximumCanHarvest = userData.getResourceCapacity(typeHarvested) - userResources.getCurrentResource(typeHarvested);
            amountHarvested = Math.min(amountHarvested,maximumCanHarvest); // can be overflow
            userResources.increaseResource(typeHarvested, amountHarvested);
            userData.save(user.getId());
            userResources.saveModel(user.getId());
            send(new ResponseStatus2(CmdDefine.HARVEST, GameConfig.HarvestStatus.SUCCESS, amountHarvested), user);
        } catch (Exception e) {
            send(new ResponseStatus2(CmdDefine.HARVEST, GameConfig.HarvestStatus.FAIL_UNKNOWN,0), user);
        }
    }

    private void cheat(User user, int type, int amount) {
        try {
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userResources == null) {

            }
            // cheat
            int amountCheat = 1000;
            if(amount>0){
                amountCheat = amount;
            }
            int ans = 0;
            switch (type){
                case 1: userResources.setGold(userResources.getGold()+amountCheat);
                    ans = userResources.getGold();
                    break;
                case 2: userResources.setElixir(userResources.getElixir() + amountCheat);
                    ans = userResources.getElixir();
                    break;
                case 3: userResources.setDarkElixir(userResources.getDarkElixir() + amountCheat);
                    ans = userResources.getDarkElixir();
                    break;
                case 4: userResources.setCoin(userResources.getCoin() + amountCheat);
                    ans = userResources.getCoin();
                    break;
            }
            userResources.saveModel(user.getId());
            send(new ResponseStatus2(CmdDefine.CHEAT_RESOURCE, type, ans), user);
        } catch (Exception e) {

        }

    }

}
