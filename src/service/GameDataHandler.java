package service;

/**
 * Created by Trungnq4 on 12/14/2018.
 */


        import bitzero.server.BitZeroServer;
        import bitzero.server.core.BZEventType;
        import bitzero.server.core.IBZEvent;
        import bitzero.server.entities.User;
        import bitzero.server.extensions.BaseClientRequestHandler;
        import bitzero.server.extensions.data.DataCmd;

        import bitzero.util.common.business.Debug;
        import cmd.CmdDefine;

        import cmd.send.ResponseGetUserData;
        import cmd.send.ResponseGetUserResources;
        import cmd.send.demo.ResponseRequestUserInfo;

        import event.eventType.DemoEventType;
        import extension.FresherExtension;

        import model.UserData;
        import model.UserResources;
        import model.oldclasses.PlayerInfo;

        import org.apache.commons.lang.exception.ExceptionUtils;


        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import util.server.ServerConstant;

        import java.util.List;
public class GameDataHandler extends BaseClientRequestHandler {
    public static short GAMEDATA_MULTI_IDS = 3000;
    private final Logger logger = LoggerFactory.getLogger("UserHandler");

    public GameDataHandler() {
        super();
    }

    public void init() {
//        getExtension().addEventListener(BZEventType.USER_DISCONNECT, this);
//        getExtension().addEventListener(BZEventType.USER_RECONNECTION_SUCCESS, this);

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
                case CmdDefine.GET_USER_DATA:
                    getUserData(user);
                    break;
                case CmdDefine.GET_USER_RESOURCES:
                    Debug.info("get resources request received");
                    getUderResources(user);
                    break;

            }
        } catch (Exception e) {
            logger.warn("USERHANDLER EXCEPTION " + e.getMessage());
            logger.warn(ExceptionUtils.getStackTrace(e));
        }

    }

    private void getUserData(User user) {
        try {
            UserData userData = (UserData) user.getProperty(ServerConstant.USER_DATA);
            send(new ResponseGetUserData(userData), user);
        } catch (Exception e) {

        }

    }

    private void getUderResources(User user) {
        // log user disconnect
        try {
            UserResources userResources = (UserResources) user.getProperty(ServerConstant.USER_RESOURCES);
            if (userResources == null) {
                userResources = new UserResources(user.getId(), user.getName());
                userResources.saveModel(user.getId());
            }
            send(new ResponseGetUserResources(userResources), user);
        } catch (Exception e) {

        }
    }

}
