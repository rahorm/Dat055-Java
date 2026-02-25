package Common;

import java.io.Serializable;

public enum RequestType implements Serializable {
    // chatroom requests
    ADD_CHATROOM, DELETE_CHATROOM, GET_CHAT_MEMBERS, GET_AVAILABLE_CHATS, ADD_CHAT_MEMBER, REMOVE_CHAT_MEMBER,
    // user requests
    ADD_USER, DELETE_USER, GET_USERS, CHECK_LOGIN,
    // msg requests
    ADD_MESSAGE, DELETE_MESSAGE, GET_MESSAGES, EDIT_MESSAGE,
}

