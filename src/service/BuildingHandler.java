package service;

import GameConfig.GameConfig;
import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import cmd.CmdDefine;
import cmd.receive.Building.RequestAddBuilding;
import cmd.receive.Building.RequestBuildingId;
import cmd.receive.Building.RequestMoveBuilding;
import cmd.send.ResponseStatus;
import cmd.send.ResponseStatus2;
import event.eventType.DemoEventType;
import extension.FresherExtension;
import model.UserData;
import model.UserResources;
import model.components.Area;
import model.components.building.Building;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AreaAttribute;
import util.Key;
import util.ResourceType;
import util.server.ServerConstant;

import java.awt.*;

/**
 * Created by Trungnq4 on 12/17/2018.
 */
public class BuildingHandler extends BaseClientRequestHandler {
    public static short BUILDING_MULTI_IDS = 4000;
    private final Logger logger = LoggerFactory.getLogger("UserHandler");

    public BuildingHandler() {
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
                case CmdDefine.ADD_BUILDING:
                    RequestAddBuilding requestAddBuilding = new RequestAddBuilding(dataCmd);
                    addNewBuilding(user,requestAddBuilding.type, requestAddBuilding.pos);
                    break;
                case CmdDefine.MOVE_BUILDING:
                    RequestMoveBuilding requestMoveBuilding = new RequestMoveBuilding(dataCmd);
                    moveBuilding(user, requestMoveBuilding.id, requestMoveBuilding.pos);
                    break;
                case CmdDefine.STOP_UPGRADING:
                    RequestBuildingId buildingIdStopUpgrade = new RequestBuildingId(dataCmd);
                    stopUpgrading(user,buildingIdStopUpgrade.id);
                    break;
                case CmdDefine.UPGRADE:
                    RequestBuildingId buildingIdUpgrade = new RequestBuildingId(dataCmd);
                    upgrade(user,buildingIdUpgrade.id);
                    break;
                case CmdDefine.UPGRADE_NOW:
                    RequestBuildingId buildingIdUpgradeNow = new RequestBuildingId(dataCmd);
                    upgradeNow(user, buildingIdUpgradeNow.id);
                    break;

            }
        } catch (Exception e) {
            logger.warn("USERHANDLER EXCEPTION " + e.getMessage());
            logger.warn(ExceptionUtils.getStackTrace(e));
        }

    }

    private void addNewBuilding(User user, Key type, Point pos) {
        try {
            // get data
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userData == null || userResources == null) {
                send(new ResponseStatus(CmdDefine.ADD_BUILDING, GameConfig.AddBuildingStatus.FAIL_UNKNOWN), user);
                return;
            }
            userData.updateBuilderWorkingAreas();

            if(type.first == GameConfig.AreaType.OBSTACLE){
                send(new ResponseStatus(CmdDefine.ADD_BUILDING,GameConfig.AddBuildingStatus.FAIL_NOT_VALID_TYPE), user);
                return;
            }

            // check enough resources
            ResourceType resourceRequired = AreaAttribute.getResourceBuildNew(type);
            if(resourceRequired.checkEnough(userResources) == false) {
                send(new ResponseStatus(CmdDefine.ADD_BUILDING ,GameConfig.AddBuildingStatus.FAIL_NOT_ENOUGH_RESOURCES), user);
                return;
            };
            // check worker available
            if(userData.getNumberWorkerAvailable()<=0){
                send(new ResponseStatus(CmdDefine.ADD_BUILDING ,GameConfig.AddBuildingStatus.FAIL_NO_WORKER_AVAILABLE), user);
                return;
            }
            // check valid position
            if(userData.userMap.checkIfFreeSpace(pos,AreaAttribute.getSizeBuildNew(type)) == false){
                send(new ResponseStatus(CmdDefine.ADD_BUILDING ,GameConfig.AddBuildingStatus.FAIL_NOT_VALID_POSITION), user);
            }

            // check enough level townhall
            if(AreaAttribute.getMaxNumberCanBuild(type, userData.getTownHallLevel()) < userData.getNumberByType(type)){
                send(new ResponseStatus(CmdDefine.ADD_BUILDING ,GameConfig.AddBuildingStatus.FAIL_ENOUGH_FOR_THIS_LEVEL), user);
            }

            // update Resources
            userResources.decreaseResource(resourceRequired);
            int buildingId = userData.createAndAddArea(type, pos);
            if(buildingId ==0){
                send(new ResponseStatus(CmdDefine.ADD_BUILDING, GameConfig.AddBuildingStatus.FAIL_UNKNOWN), user);
            }
            userData.saveModel(user.getId());
            userResources.saveModel(user.getId());
            send(new ResponseStatus2(CmdDefine.ADD_BUILDING, GameConfig.AddBuildingStatus.SUCCESS, buildingId), user);
        } catch (Exception e) {
            send(new ResponseStatus(CmdDefine.ADD_BUILDING, GameConfig.AddBuildingStatus.FAIL_UNKNOWN), user);
        }

    }
    private void moveBuilding(User user, int id, Point newPos) {
        try {
            // get data
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            if (userData == null) {
                send(new ResponseStatus(CmdDefine.MOVE_BUILDING ,GameConfig.MoveBuildingStatus.FAIL_UNKNOWN), user);
                return;
            }
            userData.updateBuilderWorkingAreas();
            Area area = userData.mapIdToArea.get(id);
            if(area == null || area.getType() == GameConfig.AreaType.OBSTACLE) {
                send(new ResponseStatus(CmdDefine.MOVE_BUILDING ,GameConfig.MoveBuildingStatus.FAIL_NOT_VALID_ID), user);
                return;
            }
            // check valid position
            if(!userData.userMap.checkIfFreeSpace(newPos, area.getSize())){
                send(new ResponseStatus(CmdDefine.MOVE_BUILDING ,GameConfig.MoveBuildingStatus.FAIL_NOT_VALID_POSITION), user);
                return;
            }

            userData.userMap.moveObject(id, newPos);
            userData.saveModel(user.getId());
            send(new ResponseStatus(CmdDefine.MOVE_BUILDING, GameConfig.MoveBuildingStatus.SUCCESS), user);
        } catch (Exception e) {
            send(new ResponseStatus(CmdDefine.MOVE_BUILDING ,GameConfig.MoveBuildingStatus.FAIL_UNKNOWN), user);

        }
    }

    private void upgrade(User user, int id) {
        try {
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userData == null || userResources == null) {
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_UNKNOWN), user);
                return;
            }
            userData.updateBuilderWorkingAreas();
            Area area = userData.mapIdToArea.get(id);
            if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_NOT_VALID_ID), user);
                return;
            }
            Building building = (Building) area;
            if(building.getUpgradingLevel()!=0){
                // upgrading already
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.UPGRADING_ALREADY), user);
                return;
            }

            if(building.getCurrentLevel() >= building.getMaxLevel()){
                // maxlevel
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.MAX_UPGRADE_LEVEL), user);
                return;
            }
            ResourceType resourceRequired = building.getUpgradeResourceRequire(building.getCurrentLevel());
            if(!resourceRequired.checkEnough(userResources)){
                // not enough resources
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_NOT_ENOUGH_RESOURCES), user);
                return;
            }
            if(userData.getNumberWorkerAvailable()<=0){
                // no worker available
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_NO_WORKER_AVAILABLE), user);
                return;
            }
            // check level townhall
            int levelTownHallrequired = building.getLevelTownHallRequiredToUpgrade();
            if(levelTownHallrequired > userData.getTownHallLevel()){
                send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_NOT_ENOUGH_LEVEL_TOWNHALL), user);
                return;
            }
            userResources.decreaseResource(resourceRequired);
            building.startUpgrade();
            userResources.saveModel(user.getId());
            userData.saveModel(user.getId());
            send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.SUCCESS), user);
        } catch (Exception e) {
            send(new ResponseStatus(CmdDefine.UPGRADE ,GameConfig.UpgradeStatus.FAIL_UNKNOWN), user);
        }

    }

    private void stopUpgrading(User user, int id) {
        try {
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userData == null || userResources == null) {
                send(new ResponseStatus(CmdDefine.STOP_UPGRADING ,GameConfig.StopUpgradingStatus.FAIL_UNKNOWN), user);
                return;
            }
            userData.updateBuilderWorkingAreas();
            Area area = userData.mapIdToArea.get(id);
            if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                send(new ResponseStatus(CmdDefine.STOP_UPGRADING ,GameConfig.StopUpgradingStatus.FAIL_NOT_VALID_ID), user);
                return;
            }
            Building building = (Building) area;
            if(building.getUpgradingLevel()==0){
                // not upgrading
                send(new ResponseStatus(CmdDefine.STOP_UPGRADING ,GameConfig.StopUpgradingStatus.FAIL_NOT_UPGRADING), user);
                return;
            }

            ResourceType resourceRequired = building.getUpgradeResourceRequire(building.getUpgradingLevel());
            userResources.inreaseResource(resourceRequired,0.5f);

            if(building.getCurrentLevel()>1){
                // upgrading, not constructing
                building.stopUpgrade();
            }   else    {
                // stop constructing, just need to removeArea from userData
                userData.removeArea(building.getId());
            }
            userResources.saveModel(user.getId());
            userData.saveModel(user.getId());
            send(new ResponseStatus(CmdDefine.STOP_UPGRADING ,GameConfig.StopUpgradingStatus.SUCCESS), user);
        } catch (Exception e) {
            send(new ResponseStatus(CmdDefine.STOP_UPGRADING ,GameConfig.UpgradeStatus.FAIL_UNKNOWN), user);
        }
    }

    private void upgradeNow(User user, int id) {
        try {
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userData == null || userResources == null) {
                send(new ResponseStatus(CmdDefine.UPGRADE_NOW ,GameConfig.UpgradeNowStatus.FAIL_UNKNOWN), user);
                return;
            }
            userData.updateBuilderWorkingAreas();
            Area area = userData.mapIdToArea.get(id);
            if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                send(new ResponseStatus(CmdDefine.UPGRADE_NOW ,GameConfig.UpgradeNowStatus.FAIL_NOT_VALID_ID), user);
                return;
            }
            Building building = (Building) area;
            if(building.getUpgradingLevel()==0){
                // not upgrading
                send(new ResponseStatus(CmdDefine.UPGRADE_NOW ,GameConfig.UpgradeNowStatus.FAIL_NOT_UPGRADING), user);
                return;
            }

            // TODO : current allow finish upgrade without checking and decrease coin, need to check and decrease later
            building.finishUpgrade();
            userResources.saveModel(user.getId());
            userData.saveModel(user.getId());
            send(new ResponseStatus(CmdDefine.UPGRADE_NOW ,GameConfig.UpgradeNowStatus.SUCCESS), user);
        } catch (Exception e) {
            send(new ResponseStatus(CmdDefine.UPGRADE_NOW ,GameConfig.UpgradeStatus.FAIL_UNKNOWN), user);
        }
    }

}
