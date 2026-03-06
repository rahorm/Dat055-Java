package Client;

//import Common.MsgHistoryWrapper;
import Common.ChatData;
import Common.ChatMemberData;
import Common.RequestWrapper;
import Common.UserData;
import Model.ChatRoomFacade;
import Other.Message;

import java.util.ArrayList;

public class ServerActionHandler {

    public ServerActionHandler() {
    }

    public void handle(Object obj) {

        if (!(obj instanceof RequestWrapper request)) {
            System.out.println("Unknown object received: " + obj);
            return;
        }

        System.out.println("Received request: " + request);

        ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();

        switch (request.getType()) {

            case GET_MESSAGES:
                ArrayList<Message> history = (ArrayList<Message>) request.getData();
                System.out.println("Message history received: " + history);
                facade.setHistory(history);
                break;

            case CREATE_USER:
                boolean created = (boolean) request.getData();
                //skicka vidare till facaden
                System.out.println("User has been created: " + created);
                break;

            case ADD_CHATROOM:
                ChatData updated = (ChatData) request.getData();
                facade.changeActiveRoom(updated.getChatId());
                facade.addMember(facade.getActiveUser(), updated.getChatId());
                break;

            case DELETE_CHATROOM:
                String chatRoom = (String) request.getData();
                System.out.println("Chatroom deleted: " + chatRoom);
                //yterligare actions?
                break;

            case ADD_CHAT_MEMBER:
                ChatMemberData added = (ChatMemberData) request.getData();
                System.out.println("Member added: " + added);
                facade.addMemberLocal(added.getUsername());

                if(added.getUsername().equals(facade.getActiveUser())){
                    facade.getAvailableChats(added.getUsername());
                }
                break;

            case CHECK_USER:
                boolean exists = (boolean) request.getData();
                System.out.println("User exists: " + exists);
                //fler actions?
                break;

            case GET_AVAILABLE_CHATS:
                ArrayList<ChatData> data = (ArrayList<ChatData>) request.getData();
                System.out.println("Available chats received: " + data);
                facade.setAvailableChats(data);
                break;

            case LOGIN:
                UserData userData = (UserData) request.getData();
                facade.setActiveUser(userData.getUsername());
                System.out.println("logged in clientside");
                break;
                //@todo meddela ui att användaren är inloggad

            case GET_CHAT_MEMBERS:
                ArrayList<String> members = (ArrayList<String>) request.getData();
                facade.setMemberList(members);
                break;

            default:
                System.out.println("Unhandled request type: " + request.getType());
        }

    }
}

    /*public void handle(Object obj) {
        Object objToReturn = null;

        if (obj instanceof MsgHistoryWrapper input) {

            System.out.println("Output: " + input.toString());
            ArrayList<Message> history;
            history = (ArrayList<Message>) input.getMsgHistory();
            System.out.println("Message history received: " + history);
            ChatRoomFacade facade = Model.ChatRoomFacade.getInstance();
            facade.setHistory(history);
        }


    }*/
