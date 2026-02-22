package Common;

import java.io.Serializable;

/**
 * thoughts: this is just a string now so why have a wrpper.
 * what should be contained?
 * - who made it, they are the first member
 */

public class AddChatRoomWrapper implements Serializable {

    private String chatRoom;
    private String creator;

    public AddChatRoomWrapper(String chatRoom, String creator) {
        this.chatRoom = chatRoom;
        this.creator = creator;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public String toString() {
        return "AddChatRoomWrapper contains chatRoom: " + chatRoom + ", creator: " + creator;
    }
}
