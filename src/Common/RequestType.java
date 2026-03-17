package Common;

import java.io.Serializable;

public enum RequestType implements Serializable {
    // chatroom requests
    ADD_CHATROOM, DELETE_CHATROOM, GET_CHAT_MEMBERS, GET_AVAILABLE_CHATS, ADD_MEMBER, REMOVE_CHAT_MEMBER,
    // user requests
    CREATE_USER, DELETE_USER, CHECK_USER, LOGIN,
    // msg requests
    ADD_MESSAGE, DELETE_MESSAGE, GET_MESSAGES  , //EDIT_MESSAGE,
}

