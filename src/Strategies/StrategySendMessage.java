package Strategies;

import Other.Message;
import Other.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Server.DatabaseConnection.getError;

public class StrategySendMessage implements Strategy{
    @Override
    public void executeStrategy(SendMessage msg) { //behöver objektet sendMsg
        Message message = msg.getMessage();

        int msgId = 1; //Detta behöver def fixas
        String content = message.getContent();
        LocalDateTime timestamp = message.getTimestamp();
        User sender = message.getSender();
        int chatID = message.getChatID();
        boolean hasImg = false; //Detta behöver def fixas

        try(PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ChatMessages VALUES (?, ?, ?, ?, ?, ?)");){
            ps.setString(1, String.valueOf(msgId));
            ps.setString(2, String.valueOf(chatID));
            ps.setString(3, sender.getUsername());
            ps.setString(4, String.valueOf(timestamp));
            ps.setString(5, content);
            ps.setString(6, String.valueOf(hasImg));
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("{\"success\":false, \"error\":\""+getError(e)+"\"}");
        }
        System.out.println("{\"success\":true}");
    }
}
