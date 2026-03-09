package Common;

import java.io.Serializable;

public class ChatMemberData implements Serializable{

    private String username;
    private int chatId;

    public ChatMemberData(String username, int chatId) {
        this.username = username;
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getChatId() {return chatId;}
    public void setChatId(int chatId) {this.chatId = chatId;}

    @Override
    public String toString() {
        return "ChatMemberData: member- "+username+" chatId - "+chatId;
    }
}
