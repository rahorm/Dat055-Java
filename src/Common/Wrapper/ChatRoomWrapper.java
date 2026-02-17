package Common.Wrapper;

import Other.Message;

import java.io.Serializable;

public class ChatRoomWrapper implements Serializable {

    private Message msg;

    public ChatRoomWrapper(Message msg) {
        this.msg = msg;
    }
}
