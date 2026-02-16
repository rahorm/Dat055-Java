package Other;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    public String content;
    public LocalDateTime timestamp;
    public User sender;
    public int chatID;

    public Message(User sender, int chatID, String content){
        this.content = content;
        this.sender = sender;
        this.chatID = chatID;
        this.timestamp = LocalDateTime.now();
    }
}
