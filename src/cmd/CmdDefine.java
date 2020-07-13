package cmd;


public class CmdDefine {
    public static final short CUSTOM_LOGIN = 1;
    public static final short GET_USER_INFO = 1001;
   
    //Log cmd
    public static final short MOVE = 2001;
    public static final short GET_NAME = 2002;
    public static final short SET_NAME = 2003;

    // trungnq4
    // get data
    public static final short GET_USER_DATA = 3001;
    public static final short GET_USER_RESOURCES = 3002;

    // change data
    public static final short ADD_BUILDING = 4001;
    public static final short MOVE_BUILDING = 4002;
    public static final short STOP_UPGRADING = 4003; // STOP CONSTRUCTING ALSO USED FOR THIS TYPE
    public static final short UPGRADE = 4004;
    public static final short UPGRADE_NOW = 4005;


    public static final short CHEAT_RESOURCE = 5001;
    public static final short HARVEST = 5002;


}
