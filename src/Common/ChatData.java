package Common;

import java.io.Serializable;

public class ChatData implements Serializable {
    private int chatId;
    private String chatName;

    public ChatData(int chatId, String chatName) {
        this.chatId = chatId;
        this.chatName = chatName;
    }

    public int getChatId() {
        return chatId;
    }
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    public String getChatName() {
        return chatName;
    }
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    public String toString(){
        return "Chatdata: chatName - "+chatName+" chatId - "+chatId;
    }
}
