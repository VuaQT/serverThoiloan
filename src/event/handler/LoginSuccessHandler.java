package event.handler;

import bitzero.server.core.BZEvent;
import bitzero.server.core.BZEventParam;
import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseServerEventHandler;
import bitzero.server.extensions.ExtensionLogLevel;

import bitzero.util.ExtensionUtility;

import java.util.HashMap;
import java.util.Map;

import bitzero.util.socialcontroller.bean.UserInfo;
import event.eventType.DemoEventParam;
import event.eventType.DemoEventType;
import model.UserData;
import model.UserResources;
import model.oldclasses.PlayerInfo;
import util.database.DataModel;
import util.server.ServerConstant;

public class LoginSuccessHandler extends BaseServerEventHandler {
    public LoginSuccessHandler() {
        super();
    }

    public void handleServerEvent(IBZEvent iBZEvent) {
        this.onLoginSuccess((User) iBZEvent.getParameter(BZEventParam.USER));
    }

    /**
     * @param user
     * description: after login successful to server, core framework will dispatch this event
     */
    private void onLoginSuccess(User user) {
        trace(ExtensionLogLevel.DEBUG, "On Login Success ", user.getName());

        PlayerInfo pInfo = null;
        try {
            pInfo = (PlayerInfo) PlayerInfo.getModel(user.getId(), PlayerInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pInfo==null){
            pInfo = new PlayerInfo(user.getId(), user.getName());
            try {
                pInfo.saveModel(user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user.setProperty(ServerConstant.PLAYER_INFO, pInfo);


        UserData userData = null;
        UserResources userResources = null;
        try {
            userData = UserData.get(user.getId());
            userResources = (UserResources) DataModel.getModel(user.getId(), UserResources.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userData.showInfo();
        user.setProperty(ServerConstant.USER_DATA, userData);

        if(userResources == null){
            userResources = new UserResources(user.getId(), user.getName());
            try{
                userResources.saveModel(user.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        user.setProperty(ServerConstant.USER_RESOURCES, userResources);

        /**
         * cache playerinfo in RAM
         */


        /**
         * send login success to client
         * after receive this message, client begin to send game logic packet to server
         */
        ExtensionUtility.instance().sendLoginOK(user);
        
        /**
         * dispatch event here
         */
        Map evtParams = new HashMap();
        evtParams.put(DemoEventParam.USER, user);
        evtParams.put(DemoEventParam.NAME, user.getName());
        ExtensionUtility.dispatchEvent(new BZEvent(DemoEventType.LOGIN_SUCCESS, evtParams));

    }

}
