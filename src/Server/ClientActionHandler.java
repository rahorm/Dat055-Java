package Server;

import Common.MsgHistoryWrapper;
import Common.RequestType;
import Common.RequestWrapper;
import Common.SendMsgWrapper;
import Other.Message;

import java.sql.SQLException;


public class ClientActionHandler {

    private DatabaseConnection DBcon;

    public ClientActionHandler() {
        try {
            DBcon = DatabaseConnection.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object handle(Object obj) {

        if (!(obj instanceof RequestWrapper request)) {
            System.out.println("Unknown object received: " + obj);
            return null;
        }

        Object objToReturn = null;

        switch (request.getType()) {

            case SEND_MESSAGE -> {
                Message message = (Message) request.getData();
                DBcon.sendMsg(message);

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MESSAGES,
                        DBcon.getChatMessages(message.getChatID())
                );
            }

            case CREATE_CHATROOM -> {
                String chatRoomName = (String) request.getData();
                DBcon.createChatRoom(666, chatRoomName);

                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats()
                );
            }

            case DELETE_CHATROOM -> {
                int chatRoomName = (Integer) request.getData();
                DBcon.deleteChatRoom(chatRoomName);

                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats()
                );
            }

            case CHECK_USER_EXISTS -> {
                String username = (String) request.getData();
                boolean exists = DBcon.checkUserExists(username);

                objToReturn = new RequestWrapper(
                        RequestType.CHECK_USER_EXISTS,
                        exists
                );
            }

            case CHECK_LOGIN -> {
                String username = (String) request.getData();
                boolean valid = DBcon.checkLogin(username);

                objToReturn = new RequestWrapper(
                        RequestType.CHECK_LOGIN,
                        valid
                );
            }

            case CREATE_USER -> {
                String username = (String) request.getData();
                DBcon.createUser(username);

                objToReturn = new RequestWrapper(
                        RequestType.CREATE_USER,
                        true
                );
            }

            case DELETE_USER -> {
                String username = (String) request.getData();
                DBcon.deleteUser(username);

                objToReturn = new RequestWrapper(
                        RequestType.DELETE_USER,
                        true
                );
            }

            case ADD_CHAT_MEMBER -> {
                // example format: "chatID:username"
                String data = (String) request.getData();
                String[] parts = data.split(":");
                int chatID = Integer.parseInt(parts[0]);
                String username = parts[1];

                DBcon.addChatMember(chatID, username);

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MEMBERS,
                        DBcon.getChatMembers(chatID)
                );
            }

            case REMOVE_CHAT_MEMBER -> {
                String data = (String) request.getData();
                String[] parts = data.split(":");
                int chatID = Integer.parseInt(parts[0]);
                String username = parts[1];

                DBcon.removeChatMember(chatID, username);

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MEMBERS,
                        DBcon.getChatMembers(chatID)
                );
            }

            case EDIT_MESSAGE -> {
                Message message = (Message) request.getData();
                DBcon.editMsg(message);

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MESSAGES,
                        DBcon.getChatMessages(message.getChatID())
                );
            }

            case DELETE_MESSAGE -> {
                Message message = (Message) request.getData();
                DBcon.deleteMsg(message);

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MESSAGES,
                        DBcon.getChatMessages(message.getChatID())
                );
            }

            case GET_CHAT_MESSAGES -> {
                int chatID = (int) request.getData();

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MESSAGES,
                        DBcon.getChatMessages(chatID)
                );
            }

            case GET_CHAT_MEMBERS -> {
                int chatID = (int) request.getData();

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MEMBERS,
                        DBcon.getChatMembers(chatID)
                );
            }

            case GET_AVAILABLE_CHATS -> {
                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats()
                );
            }

            default -> System.out.println("Unhandled request type: " + request.getType());
        }

        return objToReturn;
    }
//
//    public Object handle(Object obj){
//        Object objToReturn = null;
//
//        if(obj instanceof SendMsgWrapper){
//            SendMsgWrapper input = (SendMsgWrapper)obj;
//            Message message = input.getMsg();
//            DBcon.sendMsg(message);
//            objToReturn = new MsgHistoryWrapper(DBcon.getChatMessages(message.getChatID()));
//        }
//        /* Objects that need handling
//        createChatRoom
//        deleteChatRoom
//        checkUserExists
//        checkLogIn
//        createUser
//        deleteUser
//        addChatMember
//        removeChatMember
//        sendMsg
//        editMsg
//        deleteMsg
//        getChatMessages
//        getChatMembers
//        getAvailableChats*/
//
//        return objToReturn;
//    }
}
