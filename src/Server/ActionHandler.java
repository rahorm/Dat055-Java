package Server;

import Other.Message;
import Other.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Server.DatabaseConnection.*;

public class ActionHandler {

    private DatabaseConnection DBcon;

    public void handle(Object obj){
        if(obj instanceof SendMessage){
            Message message = msg.getMessage();

            try(PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO ChatMessages VALUES (?, ?, ?, ?, ?, ?)");){
                ps.setString(1, "1"); //needs fixing
                ps.setString(2, String.valueOf(message.getChatID()));
                ps.setString(3, String.valueOf(message.getSender()));
                ps.setString(4, String.valueOf(message.getTimestamp()));
                ps.setString(5, message.getContent());
                ps.setString(6, "false"); //needs fixing
                ps.executeUpdate();
            }  catch (SQLException e) {
                System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
            }
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
