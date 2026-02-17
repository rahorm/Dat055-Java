package Server;

import Common.SendMsgWrapper;
import Other.Message;
import Other.User;

import java.sql.SQLException;
import java.time.LocalDateTime;


public class ActionHandler {

    private DatabaseConnection DBcon;

    public ActionHandler() {
        try {
            DBcon = DatabaseConnection.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void handle(Object obj){
        if(obj instanceof SendMsgWrapper){
            SendMsgWrapper input = (SendMsgWrapper)obj;
            Message message = input.getMsg();
            DBcon.sendMsg(message);
            //1. ask dbcon for msgHistory
            //2. package as whatever
            //3. send to all clients
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
    }
}
