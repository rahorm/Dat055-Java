package Common;

public enum RequestType {
    ADD_CHATROOM,
    DELETE_CHATROOM,
    ADD_USER,
    SEND_MESSAGE,
    GET_MESSAGE_HISTORY
}

/**
 * RequestWrapper w = new RequestWrapper(RequestType.SEND_MESSAGE, msg);
 * out.writeObject(w);
 * */
