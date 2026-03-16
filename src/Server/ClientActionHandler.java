package Server;

//import Common.MsgHistoryWrapper;
import Common.*;
//import Common.SendMsgWrapper;
import Model.ChatRoomFacade;
import Other.Message;
import Other.PictureMessage;

import java.sql.SQLException;

/**
 * logic for what we are to do with data that clienthandler recieves from clients
 * */
public class ClientActionHandler {

    private DatabaseConnection DBcon;

    public ClientActionHandler() {
        try {
            DBcon = DatabaseConnection.getInstance();
            System.out.println("✅ DB connected!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ DB error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Object handle(Object obj) {

        if (!(obj instanceof RequestWrapper request)) {
            System.out.println("Unknown object received: " + obj);
            return null;
        }

        Object objToReturn = null;

        switch (request.getType()) {

            case ADD_MESSAGE -> {
                if (request.getData() instanceof PictureMessage) {
                    System.out.println("Picturemessage being sent");
                    PictureMessage message = (PictureMessage) request.getData();
                    DBcon.sendMsg(message);
                    objToReturn = new RequestWrapper(
                            RequestType.GET_MESSAGES,
                            DBcon.getChatMessages(message.getChatID())
                    );
                } else {
                    System.out.println("normal message being sent");
                    Message message = (Message) request.getData();
                    DBcon.sendMsg(message);
                    objToReturn = new RequestWrapper(
                            RequestType.GET_MESSAGES,
                            DBcon.getChatMessages(message.getChatID())
                    );
                }
            }

            case ADD_CHATROOM -> {
                ChatData chatData = (ChatData) request.getData();
                DBcon.createChatRoom(chatData.getChatId(), chatData.getChatName());

                // return updated list instead of just the new chatData
                ChatRoomFacade model = ChatRoomFacade.getInstance();
                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats(model.getActiveUser())
                );
            }

            case DELETE_CHATROOM -> {
                int chatRoomName = (Integer) request.getData();
                DBcon.deleteChatRoom(chatRoomName);
                ChatRoomFacade model = ChatRoomFacade.getInstance();

                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats(model.getActiveUser())
                );
            }

            case CHECK_USER -> {
                String username = (String) request.getData();
                boolean exists = DBcon.checkUserExists(username);

                objToReturn = new RequestWrapper(
                        RequestType.CHECK_USER,
                        exists
                );
            }

            case LOGIN -> {
                System.out.println("Server handling login");
                UserData userdata = (UserData) request.getData();
                boolean success = DBcon.checkLogin(userdata.getUsername(), userdata.getPassword());
                Object[] data = {success, new UserData(userdata.getUsername(), userdata.getPassword())};

                objToReturn = new RequestWrapper(
                        RequestType.LOGIN,
                        data
                );
                System.out.println("Server finished trying to log in");
            }

            case CREATE_USER -> {
                UserData userdata = (UserData) request.getData();
                boolean created = DBcon.createUser(userdata.getUsername(), userdata.getPassword());

                objToReturn = new RequestWrapper(
                        RequestType.CREATE_USER,
                        created
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

            case ADD_MEMBER -> {
                ChatMemberData data = (ChatMemberData) request.getData();
                DBcon.addMember(data.getChatId(), data.getUsername());

                objToReturn = new RequestWrapper(
                        RequestType.ADD_MEMBER,
                        data
                );
            }

            case REMOVE_CHAT_MEMBER -> {
                ChatMemberData data = (ChatMemberData) request.getData();

                DBcon.removeChatMember(data.getChatId(), data.getUsername());

                objToReturn = new RequestWrapper(
                        RequestType.GET_CHAT_MEMBERS,
                        DBcon.getChatMembers(data.getChatId())
                );
            }

            case DELETE_MESSAGE -> {
                Message message = (Message) request.getData();
                DBcon.deleteMsg(message);

                objToReturn = new RequestWrapper(
                        RequestType.GET_MESSAGES,
                        DBcon.getChatMessages(message.getChatID())
                );
            }

            case GET_MESSAGES -> {
                int chatID = (int) request.getData();

                objToReturn = new RequestWrapper(
                        RequestType.GET_MESSAGES,
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
                String user = (String) request.getData();
                objToReturn = new RequestWrapper(
                        RequestType.GET_AVAILABLE_CHATS,
                        DBcon.getAvailableChats(user)
                );
            }

            default -> System.out.println("Unhandled request type: " + request.getType());
        }

        return objToReturn;
    }
}
