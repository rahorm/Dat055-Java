package Other;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Message implements Serializable {
    private String content;
    private LocalDateTime timestamp;
    private String sender;
    private int chatID;
    private int messageID;
    private HashMap<String, String> hashMap;


    public Message(String sender, int chatID, String content){
        this.content = content;
        this.sender = sender;
        this.chatID = chatID;
        this.timestamp = LocalDateTime.now();
        IdGenerator generator = IdGenerator.getInstance();
        this.messageID = generator.generateId();
        this.hashMap = new HashMap<>();
    }
    public Message(int msgId, String sender, int chatID, String content, LocalDateTime time){
        this.content = content;
        this.sender = sender;
        this.chatID = chatID;
        this.timestamp = time;
        this.messageID = msgId;
    }

    public void addAttachment(String key, String value) {
        hashMap.put(key, value);
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public boolean getHasImg() {
        return hashMap != null && !hashMap.isEmpty();
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String toString(){
        return "msgId: "+this.messageID+", sender: "+this.sender+", chatId: "+this.chatID+", time: "+this.timestamp+", content: "+this.content+", hasImg: ";
    }

}
