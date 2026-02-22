package Common;

public enum RequestType {
    CREATE_CHATROOM,
    DELETE_CHATROOM,
    CHECK_USER_EXISTS,
    CHECK_LOGIN,
    CREATE_USER,
    DELETE_USER,
    ADD_CHAT_MEMBER,
    REMOVE_CHAT_MEMBER,
    SEND_MESSAGE,
    EDIT_MESSAGE,
    DELETE_MESSAGE,
    GET_CHAT_MESSAGES,
    GET_CHAT_MEMBERS,
    GET_AVAILABLE_CHATS
}

/**
 * RequestWrapper w = new RequestWrapper(RequestType.SEND_MESSAGE, msg);
 * out.writeObject(w);
 * */
