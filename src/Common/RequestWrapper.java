package Common;

import Model.ChatRoomFacade;

import java.io.Serializable;

public class RequestWrapper implements Serializable {

    private RequestType type;
    private Object data; // can hold Message, User, ChatRoom, String, etc.
    private ChatRoomFacade chatRoomFacade = ChatRoomFacade.getInstance();
    private String activeUser;

    public RequestWrapper(RequestType type, Object data) {
        this.type = type;
        this.data = data;
        this.activeUser = chatRoomFacade.getActiveUser();
    }

    public RequestType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RequestWrapper type=" + type + ", data=" + data;
    }
}
