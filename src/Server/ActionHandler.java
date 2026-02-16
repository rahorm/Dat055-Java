package Server;

import Other.Message;
import Other.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Server.DatabaseConnection.*;

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
        if(obj instanceof SendMessage){
            Message message = obj.getMessage();
            DBcon.sendMsg(message);
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
