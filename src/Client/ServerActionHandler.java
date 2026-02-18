package Client;

import Common.MsgHistoryWrapper;
import Common.SendMsgWrapper;
import Other.Message;
import Server.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;


public class ServerActionHandler {

    public ServerActionHandler() {
    }

    public void handle(Object obj){
        Object objToReturn = null;

        if(obj instanceof MsgHistoryWrapper){
            MsgHistoryWrapper input = (MsgHistoryWrapper)obj;
            // MODEL FACADE CONNECTION? input.getMsgHistory();
        }
    }
}
