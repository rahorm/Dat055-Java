package Other;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String content;
    private LocalDateTime timestamp;
    private User sender;
    private int chatID;
    private int messageID;

    public Message(User sender, int chatID, String content){
        this.content = content;
        this.sender = sender;
        this.chatID = chatID;
        this.timestamp = LocalDateTime.now();
        IdGenerator generator = IdGenerator.getInstance();
        this.messageID = generator.generateId();
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getChatID() {
        return chatID;
    }
    public void setChatID(int chatID) {
        this.chatID = chatID;
    }
}
