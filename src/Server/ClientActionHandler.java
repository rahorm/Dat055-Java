package Server;

import Common.MsgHistoryWrapper;
import Common.SendMsgWrapper;
import Other.Message;

import java.sql.SQLException;


public class ClientActionHandler {

    private DatabaseConnection DBcon;

    public ClientActionHandler() {
        try {
            DBcon = DatabaseConnection.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public Object handle(Object obj){
        Object objToReturn = null;

        if(obj instanceof SendMsgWrapper){
            SendMsgWrapper input = (SendMsgWrapper)obj;
            Message message = input.getMsg();
            DBcon.sendMsg(message);
            objToReturn = new MsgHistoryWrapper(DBcon.getChatMessages(message.getChatID()));
        }
        /* Objects that need handling
        createChatRoom
        deleteChatRoom
        checkUserExists
        checkLogIn
        createUser
        deleteUser
        addChatMember
        removeChatMember
        sendMsg
        editMsg
        deleteMsg
        getChatMessages
        getChatMembers
        getAvailableChats*/

        return objToReturn;
    }
}
