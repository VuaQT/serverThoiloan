package event.handler;

import bitzero.server.core.BZEventParam;
import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseServerEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.server.ServerConstant;

public class LogoutHandler extends BaseServerEventHandler {

    private final Logger logger = LoggerFactory.getLogger("LogoutHandler");

    public LogoutHandler() {
        super();
    }

    public void handleServerEvent(IBZEvent iBZEvent) {
        this.onLogOut((User) iBZEvent.getParameter(BZEventParam.USER));
    }

    private void onLogOut(User user) {
        System.out.println("LOG OUT RUI, DELETE USER DATA IN CACHE");
        user.removeProperty(ServerConstant.USER_DATA);
        user.removeProperty(ServerConstant.USER_RESOURCES);
//        LogObject logObject = new LogObject(LogObject.ACTION_LOGOUT);
//        logObject.zingId = Long.valueOf((String) user.getProperty("zingId"));
//        logObject.zingName = (String) user.getProperty("zingName");
//        logObject.accountType = (Integer) user.getProperty("accountType");
//        logObject.openAccount = (String) user.getProperty("openAccount");
//        long creationTime = 0;
//        if (user.getSession() != null)
//            creationTime = System.currentTimeMillis() - user.getSession().getCreationTime();
//        logObject.quantity = Math.round(creationTime / 1000);
//        //System.out.println("Log logout = " + logObject.getLogMessage() + "\nCreation time = " + creationTime);
//        MetricLog.writeActionLog(logObject);

    }


}
