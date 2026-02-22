package Common;

import java.io.Serializable;

public class DeleteChatRoomWrapper implements Serializable {

    private String chatRoom; // or int chatRoomId

    public DeleteChatRoomWrapper(String chatRoomName) {
        this.chatRoom = chatRoomName;
    }

    public String getChatRoomName() {
        return chatRoom;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoom = chatRoomName;
    }

    @Override
    public String toString() {
        return "DeleteChatRoomWrapper contains chatRoomName: " + chatRoom;
    }
}
