package Client;

import Common.MsgHistoryWrapper;
import Common.SendMsgWrapper;
import Other.Message;
import Server.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;


public class ServerActionHandler {
    Model.ChatRoomFacade facade;

    public ServerActionHandler() {
        facade = Model.ChatRoomFacade.getInstance();
    }

    public void handle(Object obj){
        Object objToReturn = null;

        if(obj instanceof MsgHistoryWrapper input){

            System.out.println("Output: "+input.toString());
            // MODEL FACADE CONNECTION? input.getMsgHistory();
        }
    }
}
