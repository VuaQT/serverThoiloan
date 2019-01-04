package extension;


import GameConfig.GameConfig;
import bitzero.engine.sessions.ISession;
import bitzero.server.config.ConfigHandle;
import bitzero.server.core.BZEventType;
import bitzero.server.entities.User;
import bitzero.server.entities.managers.ConnectionStats;
import bitzero.server.extensions.BZExtension;
import bitzero.server.extensions.data.DataCmd;
import bitzero.util.ExtensionUtility;
import bitzero.util.common.business.Debug;
import bitzero.util.datacontroller.business.DataController;
import bitzero.util.socialcontroller.bean.UserInfo;
import cmd.receive.authen.RequestLogin;
import event.handler.LoginSuccessHandler;
import event.handler.LogoutHandler;
import model.UserData;
import model.components.Area;
import model.components.building.Building;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;
import service.*;
import util.AreaAttribute;
import util.GuestLogin;
import util.Key;
import util.ResourceType;
import util.metric.LogObject;
import util.metric.MetricLog;
import util.server.ServerConstant;
import util.server.ServerLoop;

import java.awt.*;
import java.util.List;
import java.util.Scanner;


public class FresherExtension extends BZExtension {
    private static String SERVERS_INFO =
        ConfigHandle.instance().get("servers_key") == null ? "servers" : ConfigHandle.instance().get("servers_key");

    private ServerLoop svrLoop;

    public FresherExtension() {
        super();
        setName("Fresher");
        svrLoop = new ServerLoop();
    }

    public void init() {

        /**
         * register new handler to catch client's packet
         */
        trace("  Register Handler ");
        addRequestHandler(UserHandler.USER_MULTI_IDS, UserHandler.class);
        addRequestHandler(DemoHandler.DEMO_MULTI_IDS, DemoHandler.class);
        addRequestHandler(GameDataHandler.GAMEDATA_MULTI_IDS, GameDataHandler.class);
        addRequestHandler(BuildingHandler.BUILDING_MULTI_IDS, BuildingHandler.class);
        addRequestHandler(HarvestHandler.HARVEST_MULTI_IDS, HarvestHandler.class);


        /**
         * register new event
         */
        trace(" Event Handler ");
        addEventHandler(BZEventType.USER_LOGIN, LoginSuccessHandler.class);
        addEventHandler(BZEventType.USER_LOGOUT, LogoutHandler.class);
        addEventHandler(BZEventType.USER_DISCONNECT, LogoutHandler.class);

        GameConfig.loadData();

        //TEST();
    }

    public ServerLoop getServerLoop() {
        return svrLoop;
    }

    @Override
    public void monitor() {
        try {
            ConnectionStats connStats = bz.getStatsManager().getUserStats();
            JSONObject data = new JSONObject();

            data.put("totalInPacket", bz.getStatsManager().getTotalInPackets());
            data.put("totalOutPacket", bz.getStatsManager().getTotalOutPackets());
            data.put("totalInBytes", bz.getStatsManager().getTotalInBytes());
            data.put("totalOutBytes", bz.getStatsManager().getTotalOutBytes());

            data.put("connectionCount", connStats.getSocketCount());
            data.put("totalUserCount", bz.getUserManager().getUserCount());

            DataController.getController().setCache(SERVERS_INFO, 60 * 5, data.toString());
        } catch (Exception e) {
            trace("Ex monitor");
        }
    }

    @Override
    public void destroy() {
        List<User> allUser = ExtensionUtility.globalUserManager.getAllUsers();
        if (allUser.size() == 0)
            return;

        User obj = null;

        for (int i = 0; i < allUser.size(); i++) {
            obj = allUser.get(i);
            // do sth with user
            LogObject logObject = new LogObject(LogObject.ACTION_LOGOUT);
            logObject.zingId = obj.getId();
            logObject.zingName = obj.getName();
            //System.out.println("Log logout = " + logObject.getLogMessage());
            MetricLog.writeActionLog(logObject);
        }
    }

    /**
     *
     * @param cmdId
     * @param session
     * @param objData
     *
     * the first packet send from client after handshake success will dispatch to doLogin() function
     */
    public void doLogin(short cmdId, ISession session, DataCmd objData) {
        RequestLogin reqGet = new RequestLogin(objData);
        reqGet.unpackData();
       
        try {
            
            UserInfo uInfo = getUserInfo(reqGet, session.getAddress());
            User u = ExtensionUtility.instance().canLogin(uInfo, "", session);
            if (u!=null)
                u.setProperty("userId", uInfo.getUserId());            
        } catch (Exception e) {
            Debug.warn("DO LOGIN EXCEPTION " + e.getMessage());
            Debug.warn(ExceptionUtils.getStackTrace(e));
        }

    }

    private UserInfo getUserInfo(RequestLogin requestLogin, String ipAddress) throws Exception {
        int customLogin = ServerConstant.CUSTOM_LOGIN;
        switch(customLogin){
            case 1: // login zingme
                return ExtensionUtility.getUserInfoFormPortal(requestLogin.username);
            case 2: // set direct userid
                System.out.println(" get login set info " + requestLogin.uid + " : " + requestLogin.username);
                return GuestLogin.setInfo(requestLogin.uid, requestLogin.username);
            default: // auto increment
                return GuestLogin.newGuest();
        }        
    }
    public void TEST(){
        // TEST
        UserData userData = null;
        int uid = 421;
        try {
            userData = UserData.get(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        while (true){
            userData.updateBuilderWorkingAreas();
            userData.showInfo();
            System.out.println("Please choose operation : ");
            System.out.println("1: add new building");
            System.out.println("2: move building");
            System.out.println("3: upgrade building");
            System.out.println("4: upgrade now");
            System.out.println("5: stop upgrade");
            int operation = sc.nextInt();
            switch (operation){
                case 1:
                    System.out.println("input for create new building : type1, type2, posx, posy : ");
                    int type_1 = sc.nextInt();
                    int type_2 = sc.nextInt();
                    int pos_x = sc.nextInt();
                    int pos_y = sc.nextInt();
                    try{
                        userData.updateBuilderWorkingAreas();
                        Key type = new Key(type_1,type_2);
                        Point pos = new Point(pos_x,pos_y);
                        if(type.first == GameConfig.AreaType.OBSTACLE){
                            System.out.println("AddBuildingStatus.FAIL_NOT_VALID_TYPE");
                            return;
                        }
                        // check worker available
                        if(userData.getNumberWorkerAvailable()<=0){
                            System.out.println("AddBuildingStatus.FAIL_NO_WORKER_AVAILABLE");
                            break;
                        }
                        // check valid position
                        if(!userData.userMap.checkIfFreeSpace(pos, AreaAttribute.getSizeBuildNew(type))){
                            System.out.println("AddBuildingStatus.FAIL_NOT_VALID_POSITION");
                            break;
                        }

                        // check enough level townhall
                        if(userData.getMaxNumberCanBuild(type) <= userData.getNumberByType(type)){
                            System.out.println("AddBuildingStatus.FAIL_ENOUGH_FOR_THIS_LEVEL");
                            break;
                        }

                        int buildingId = userData.createAndAddArea(type, pos);
                        if(buildingId ==0){
                            System.out.println("AddBuildingStatus.FAIL_UNKNOWN");
                            break;
                        }
                        try {
                            userData.save(uid);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("AddBuildingStatus.SUCCESS");
                        break;
                    }catch(Exception e){
                        System.out.println("AddBuildingStatus - FAIL_UNKNOWN");
                    }
                    break;
                case 2:
                    System.out.println("input for move building : id, newPosX, newPosY ");
                    int id = sc.nextInt();
                    int newPosX = sc.nextInt();
                    int newPosY = sc.nextInt();
                    Point newPos = new Point(newPosX,newPosY);
                    try{
                        Area area = userData.getAreaById(id);
                        if(area == null || area.getType() == GameConfig.AreaType.OBSTACLE) {
                            System.out.println("MoveBuildingStatus.FAIL_NOT_VALID_ID");
                            break;
                        }
                        // check valid position
                        if(!userData.userMap.checkIfFreeSpaceToMove(area.getId(), newPos)){
                            System.out.println("MoveBuildingStatus.FAIL_NOT_VALID_POSITION");
                            break;
                        }

                        userData.userMap.moveObject(id, newPos);
                        userData.save(uid);
                        System.out.println("MoveBuildingStatus.SUCCESS");
                    }   catch (Exception e){
                        System.out.println("MoveBuildingStatus - FAIL_UNKNOWN");
                    }
                    break;
                case 3:
                    System.out.println("input for upgrade : building id");
                    id = sc.nextInt();
                    try {
                        userData.updateBuilderWorkingAreas();
                        Area area = userData.getAreaById(id);
                        if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                            System.out.println("UpgradeStatus.FAIL_NOT_VALID_ID");
                            break;
                        }
                        Building building = (Building) area;
                        if(building.getUpgradingLevel()!=0){
                            // upgrading already
                            System.out.println("UpgradeStatus.UPGRADING_ALREADY");
                            break;
                        }

                        if(building.getCurrentLevel() >= building.getMaxLevel()){
                            // maxlevel
                            System.out.println("UpgradeStatus.MAX_UPGRADE_LEVEL");
                            break;
                        }

                        // check level townhall
                        int levelTownHallrequired = building.getLevelTownHallRequiredToUpgrade();
                        if(levelTownHallrequired > userData.getTownHallLevel()){
                            System.out.println("UpgradeStatus.FAIL_NOT_ENOUGH_LEVEL_TOWNHALL");
                            break;
                        }
                        building.startUpgrade();
                        userData.save(uid);
                        System.out.println("UpgradeStatus.SUCCESS");
                    }   catch (Exception e){
                        System.out.println("UpgradeStatus - FAIL_UNKNOWN");
                    }
                    break;
                case 4:
                    System.out.println("input for upgrade now :  building id");
                    id = sc.nextInt();
                    try{
                        userData.updateBuilderWorkingAreas();
                        Area area = userData.getAreaById(id);
                        if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                            System.out.println("UpgradeNowStatus.FAIL_NOT_VALID_ID");
                            break;
                        }
                        Building building = (Building) area;
                        if(building.getUpgradingLevel()==0){
                            // not upgrading
                            System.out.println("UpgradeNowStatus.FAIL_NOT_UPGRADING");
                            break;
                        }

                        // TODO : current allow finish upgrade without checking and decrease coin, need to check and decrease later
                        building.finishUpgrade();
                        userData.save(uid);
                        System.out.println("UpgradeNowStatus.SUCCESS");
                    }   catch (Exception e){
                        System.out.println("UpgradeNowStatus - FAIL_UNKNOWN");
                    }
                    break;
                case 5:
                    System.out.println("input for upgrade now :  building id");
                    id = sc.nextInt();
                    try{
                        userData.updateBuilderWorkingAreas();
                        Area area = userData.getAreaById(id);
                        if(area == null || area.getType()==GameConfig.AreaType.OBSTACLE) {
                            System.out.println("StopUpgradingStatus.FAIL_NOT_VALID_ID");
                            return;
                        }
                        Building building = (Building) area;
                        if(building.getUpgradingLevel()==0){
                            // not upgrading
                            System.out.println("StopUpgradingStatus.FAIL_NOT_UPGRADING");
                            return;
                        }

                        ResourceType resourceRequired = building.getUpgradeResourceRequire(building.getUpgradingLevel());
                        // TODO: check overflow capacity
                        if(building.getCurrentLevel()>1){
                            // upgrading, not constructing
                            building.stopUpgrade();
                        }   else    {
                            // stop constructing, just need to removeArea from userData
                            userData.removeArea(building.getId());
                        }
                        userData.save(uid);
                        System.out.println("StopUpgradingStatus.SUCCESS");
                    }   catch (Exception e){
                        System.out.println("StopUpgradingStatus - FAIL_UNKNOWN");
                    }
            }
        }
    }
}
